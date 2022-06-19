import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import constants.Constants;
import context.StageContext;
import entities.User;
import enums.RouteEnum;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var stage = getStage();
                CONTEXT_USER = new UserUtils().getContextUser(stage);

                var followersObservableList = FXCollections.observableArrayList(
                        CONTEXT_USER.getFollowers());

                for (User user : followersObservableList) {
                    var followerView = MessageFormat.format(Constants.ViewConstants.FOLLOWS_STRUCTURE,
                            user.getId(), user.getName(), user.getUser());

                    followers.getItems().add(followerView);
                }
            }
        });
    }
}
