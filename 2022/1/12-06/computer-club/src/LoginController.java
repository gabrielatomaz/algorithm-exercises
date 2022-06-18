import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import context.UserContext;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    private static final String FILES_USERS_TXT = "src/users.txt";
    private static final String LAYOUT_VALUE = "layout-menu.fxml";
    private static final String SCENE_TITLE = "Clubinho da Computação";

    @FXML
    private Label loginText;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;

    @FXML
    void login(ActionEvent event) throws IOException, ClassNotFoundException {
        if (isUserFound()) {
            var stage = new Stage();
            var path = getClass().getResource(LAYOUT_VALUE);
            var fxmlLoader = new FXMLLoader(path);
            var root = (Parent) fxmlLoader.load();
            var scene = new Scene(root);

            stage.setTitle(SCENE_TITLE);
            stage.setScene(scene);
            stage.show();

            var node = (Node) event.getSource();
            node.getScene().getWindow().hide();
        } else {
            loginText.setText(loginText.getText().concat(": senha ou usuário errado."));
        }
    }

    private Boolean isUserFound() throws ClassNotFoundException, IOException {
        try {
            var fileIn = new FileInputStream(FILES_USERS_TXT);
            var objectIn = new ObjectInputStream(fileIn);
            var keepReading = Boolean.TRUE;
            try {
                while (keepReading) {
                    User user = (User) objectIn.readObject();
                    if (this.user.getText().equalsIgnoreCase(user.getUser())
                            && this.password.getText().equals(user.getPassword())) {
                        objectIn.close();

                        UserContext.getInstance().setUser(user);

                        return Boolean.TRUE;
                    }
                    
                    objectIn = new ObjectInputStream(fileIn);
                }

                objectIn.close();
            } catch (EOFException e) {
                keepReading = false;
                objectIn.close();

                return Boolean.FALSE;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
