import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import context.StageContext;
import entities.Post;
import entities.User;
import enums.AvatarEnum;
import enums.RouteEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import utils.FileUtils;

public class LoginController extends StageContext implements Initializable {

    private User CONTEXT_USER;

    @FXML
    private Label loginText;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;

    @FXML
    private void login(ActionEvent event) throws IOException, ClassNotFoundException {
        if (isUserFound()) {
            goTo(event, RouteEnum.MENU, CONTEXT_USER);
        } else {
            var alert = new Alert(AlertType.WARNING);
            alert.setContentText("Senha e/ou usuário errado(s)!");
            alert.show();
        }
    }

    private Boolean isUserFound() {
        var users = FileUtils.getAllUsersFromFile();

        for (User user : users) {
            if (this.user.getText().equalsIgnoreCase(user.getUser())
                    && this.password.getText().equals(user.getPassword())) {
                CONTEXT_USER = user;

                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // var user = new User(
        // "admin",
        // "admin",
        // "UFP31",
        // "admin",
        // "admin",
        // "admin",
        // "admin",
        // "admin",
        // new ArrayList<String>(),
        // "admin",
        // Boolean.TRUE,
        // new ArrayList<Post>(),
        // new ArrayList<User>(),
        // new ArrayList<User>(),
        // AvatarEnum.DEFAULT.getName(),
        // 1L);

        // FileUtils.addUser(user);

    }
}
