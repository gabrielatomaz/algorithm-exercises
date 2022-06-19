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
            AlertUtils.setAlert(AlertType.INFORMATION, Constants.AlertConstants.USERS_NOT_FOUND);

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
        var user = this.users.getSelectionModel().getSelectedItem();
        var userId = StringUtils.getFirstItemByDashDelimiter(user);
        System.out.println(userId);
    }
}
