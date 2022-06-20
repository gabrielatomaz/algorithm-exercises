package controllers.followings;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import constants.Constants;
import context.StageContext;
import entities.User;
import enums.RouteEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import utils.AlertUtils;
import utils.FileUtils;
import utils.StringUtils;
import utils.UserUtils;

public class FollowingsController extends StageContext implements Initializable {

    private User CONTEXT_USER;

    @FXML
    private ListView<String> followings;

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        goTo(event, RouteEnum.MENU, CONTEXT_USER);
    }

    @FXML
    private void unfollow(ActionEvent event) {
        var userToUnfollowSelected = this.followings.getSelectionModel().getSelectedItem();
        Long userIdToUnfollow = Long.parseLong(StringUtils.getFirstItemByDashDelimiter(userToUnfollowSelected));

        var users = FileUtils.getAllUsersFromFile();

        var contextUserOptional = users
                .stream()
                .filter(user -> CONTEXT_USER.getId().equals(user.getId()))
                .findFirst();

        if (contextUserOptional.isPresent()) {
            var contextUser = contextUserOptional.get();
            var userToUnfollowOptional = contextUser
                    .getFollowings()
                    .stream()
                    .filter(user -> userIdToUnfollow.equals(user.getId()))
                    .findFirst();

            users.remove(contextUser);

            userToUnfollowOptional.ifPresent(userToUnfollow -> {
                contextUser.getFollowings().remove(userToUnfollow);

                users.add(contextUser);

                CONTEXT_USER = contextUser;

                try {
                    FileUtils.updateUser(users);

                    this.followings.getItems().clear();

                    buildView();

                    AlertUtils.setAlert(AlertType.INFORMATION,
                            Constants.AlertConstants.UNFOLLOWED_USER_SUCCESS);
                } catch (Exception e) {
                    AlertUtils.setAlert(AlertType.WARNING,
                            Constants.AlertConstants.UNFOLLOWED_USER_ERROR);
                }
            });
        } else {
            AlertUtils.setAlert(AlertType.WARNING,
                    Constants.AlertConstants.UNFOLLOWED_USER_ERROR);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var stage = getStage();
                CONTEXT_USER = new UserUtils().getContextUser(stage);

                buildView();
            }
        });
    }

    private void buildView() {
        var followingsList = CONTEXT_USER.getFollowings();

        for (User user : followingsList) {
            var followingView = MessageFormat.format(Constants.ViewConstants.FOLLOWS_STRUCTURE,
                    user.getId(), user.getName(), user.getUser());

            followings.getItems().add(followingView);
        }
    }
}
