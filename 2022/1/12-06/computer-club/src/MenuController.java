import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import constants.Constants;
import context.StageContext;
import entities.User;
import enums.RouteEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import utils.AlertUtils;
import utils.ButtonUtils;
import utils.UserUtils;

public class MenuController extends StageContext implements Initializable {

    private User CONTEXT_USER;

    @FXML
    private Label loginText;

    @FXML
    private Button createUserButton;

    @FXML
    void goTo(ActionEvent event) throws IOException {
        try {
            var buttonText = ButtonUtils.getButtonText(event);

            var route = RouteEnum.findMenuRouteEnum(buttonText);

            if (RouteEnum.FEED.equals(route)) {
                var followings = CONTEXT_USER.getFollowings();

                if (Objects.isNull(followings) || followings.isEmpty()) {
                    AlertUtils.setAlert(AlertType.INFORMATION, Constants.AlertConstants.FOLLOWINGS_NOT_FOUND);

                    return;
                }
            }

            goTo(event, route, CONTEXT_USER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var stage = getStage();
                CONTEXT_USER = new UserUtils().getContextUser(stage);
                var isAdmin = Objects.nonNull(stage)
                        ? CONTEXT_USER.isAdmin()
                        : Boolean.FALSE;

                createUserButton.setVisible(isAdmin);
            }
        });
    }
}
