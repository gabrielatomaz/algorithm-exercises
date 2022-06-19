import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import context.StageContext;
import entities.User;
import enums.RouteEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
            var buttonText = getButton(event).getText();

            var route = RouteEnum.findMenuRouteEnum(buttonText);

            if (RouteEnum.FEED.equals(route)) {
                var followings = CONTEXT_USER.getFollowings();
                if (Objects.isNull(followings) || followings.isEmpty()) {
                    var alert = new Alert(AlertType.WARNING);
                    alert.setContentText("Você não segue nenhum usuário!");
                    alert.show();

                    return;
                }
            }

            goTo(event, route, CONTEXT_USER);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Button getButton(ActionEvent event) {
        return (Button) event.getSource();
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
