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

public class FollowersController extends StageContext implements Initializable {

    private User CONTEXT_USER;

    @FXML
    private ListView<String> followers;

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        goTo(event, RouteEnum.MENU, CONTEXT_USER);
    }

    @FXML
    private void removeFollower(ActionEvent event) {
        var userToRemoveFromFollowersSelected = this.followers.getSelectionModel().getSelectedItem();
        Long userIdToRemoveFromFollowers = Long
                .parseLong(StringUtils.getFirstItemByDashDelimiter(userToRemoveFromFollowersSelected));

        var users = FileUtils.getAllUsersFromFile();

        var contextUserOptional = users
                .stream()
                .filter(user -> CONTEXT_USER.getId().equals(user.getId()))
                .findFirst();

        if (contextUserOptional.isPresent()) {
            var contextUser = contextUserOptional.get();
            var userToRemoveFromFollowersOptional = contextUser
                    .getFollowers()
                    .stream()
                    .filter(user -> userIdToRemoveFromFollowers.equals(user.getId()))
                    .findFirst();

            users.remove(contextUser);

            userToRemoveFromFollowersOptional.ifPresent(userToRemoveFromFollowers -> {
                contextUser.getFollowers().remove(userToRemoveFromFollowers);

                users.add(contextUser);

                CONTEXT_USER = contextUser;

                try {
                    FileUtils.updateUser(users);

                    this.followers.getItems().clear();

                    buildView();

                    AlertUtils.setAlert(AlertType.INFORMATION,
                            Constants.AlertConstants.REMOVE_FOLLOWER_SUCCESS);
                } catch (Exception e) {
                    AlertUtils.setAlert(AlertType.WARNING,
                            Constants.AlertConstants.REMOVE_FOLLOWER_ERROR);
                }
            });
        } else {
            AlertUtils.setAlert(AlertType.WARNING,
                    Constants.AlertConstants.REMOVE_FOLLOWER_ERROR);
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
        var followersList = CONTEXT_USER.getFollowers();

        for (User user : followersList) {
            var followerView = MessageFormat.format(Constants.ViewConstants.FOLLOWS_STRUCTURE,
                    user.getId(), user.getName(), user.getUser());

            followers.getItems().add(followerView);
        }
    }
}
