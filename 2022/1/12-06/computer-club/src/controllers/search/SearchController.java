package controllers.search;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import constants.Constants;
import context.StageContext;
import entities.User;
import enums.RouteEnum;
import enums.SearchOptionsEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import utils.AlertUtils;
import utils.FileUtils;
import utils.StringUtils;
import utils.UserUtils;

public class SearchController extends StageContext implements Initializable {

    private User CONTEXT_USER;

    @FXML
    private ListView<String> users;

    private List<User> usersList = new ArrayList<>();

    @FXML
    private ComboBox<String> searchOptions;

    @FXML
    private TextField searchBy;

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        goTo(event, RouteEnum.MENU, CONTEXT_USER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Arrays.stream(SearchOptionsEnum.values())
                .forEach(option -> this.searchOptions.getItems().add(option.getName()));

        usersList = FileUtils.getAllUsersFromFile();

        usersList.forEach(user -> {
            var interests = StringUtils.joinWithCommaDelimiter(user.getInterests());

            var userView = MessageFormat.format(Constants.ViewConstants.USER_STRUCTURE,
                    user.getId(), user.getName(), interests);

            this.users.getItems().add(userView);
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var stage = getStage();
                CONTEXT_USER = new UserUtils().getContextUser(stage);
            }
        });
    }

    @FXML
    private void search(ActionEvent event) {
        var searchBy = this.searchBy.getText();
        var searchOption = this.searchOptions.getSelectionModel().getSelectedItem();

        var searchOptionEnum = SearchOptionsEnum.findSearchOptionsEnum(searchOption);

        var usersFound = this.usersList.stream()
                .filter(searchOptionEnum.searchBy(searchBy))
                .map(user -> {
                    var interests = StringUtils.joinWithCommaDelimiter(user.getInterests());

                    var userView = MessageFormat.format(Constants.ViewConstants.USER_STRUCTURE,
                            user.getId(), user.getName(), interests);

                    return userView;
                })
                .collect(Collectors.toList());

        if (usersFound.isEmpty()) {
            AlertUtils.setAlert(AlertType.INFORMATION,
                    Constants.AlertConstants.USERS_NOT_FOUND);

            return;
        }

        this.users.getItems().clear();

        usersFound.forEach(userView -> this.users.getItems().add(userView));
    }

    @FXML
    private void clearSearch(ActionEvent event) {
        this.users.getItems().clear();
        this.usersList.forEach(user -> {
            var interests = StringUtils.joinWithCommaDelimiter(user.getInterests());

            var userView = MessageFormat.format(Constants.ViewConstants.USER_STRUCTURE,
                    user.getId(), user.getName(), interests);

            this.users.getItems().add(userView);
        });
    }

    @FXML
    private void followUser(ActionEvent event) {
        var selectedUser = this.users.getSelectionModel().getSelectedItem();
        Long userId = Long.parseLong(StringUtils.getFirstItemByDashDelimiter(selectedUser));

        if (CONTEXT_USER.getId().equals(userId)) {
            AlertUtils.setAlert(AlertType.INFORMATION,
                    Constants.AlertConstants.YOU_CAN_NOT_FOLLOW_YOURSELF);

            return;
        }

        var isUserAlreadyFollowed = CONTEXT_USER
                .getFollowings()
                .stream()
                .anyMatch(following -> userId.equals(following.getId()));

        if (isUserAlreadyFollowed) {
            AlertUtils.setAlert(AlertType.INFORMATION,
                    Constants.AlertConstants.ALREADY_FOLLOWED);

            return;
        }

        var users = FileUtils.getAllUsersFromFile();

        var userOptional = users
                .stream()
                .filter(user -> userId.equals(user.getId()))
                .findFirst();

        var contextUserOptional = users
                .stream()
                .filter(user -> CONTEXT_USER.getId().equals(user.getId()))
                .findFirst();

        if (userOptional.isPresent() && contextUserOptional.isPresent()) {
            var user = userOptional.get();
            var contextUser = contextUserOptional.get();

            users.remove(contextUser);
            users.remove(user);

            contextUser.setFollowing(user);
            user.setFollower(contextUser);

            users.add(user);
            users.add(contextUser);

            CONTEXT_USER = contextUser;

            try {
                FileUtils.updateUser(users);

                AlertUtils.setAlert(AlertType.INFORMATION,
                        Constants.AlertConstants.FOLLOWED_USER_SUCCESS);
            } catch (Exception e) {
                AlertUtils.setAlert(AlertType.INFORMATION,
                        Constants.AlertConstants.FOLLOWED_USER_SUCCESS);
            }
        } else {
            AlertUtils.setAlert(AlertType.INFORMATION,
                    Constants.AlertConstants.FOLLOWED_USER_SUCCESS);
        }
    }
}
