import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class FollowingsController extends StageContext implements Initializable {

    private User CONTEXT_USER;

    @FXML
    private ListView<User> followings;

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        goTo(event, RouteEnum.MENU, CONTEXT_USER);
    }

    @FXML
    private void unfollow(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var stage = getStage();
                CONTEXT_USER = new UserUtils().getContextUser(stage);

                var followingsObservableList = FXCollections.observableArrayList(
                        CONTEXT_USER.getFollowings());

                for (User user : followingsObservableList) {
                    followings.getItems().add(user);
                }
            }
        });
    }

}
