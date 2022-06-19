import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import context.UserContext;
import entities.User;
import enums.AvatarEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        var user = new User(
            "admin",
            "admin",
            "UFP31",
            "admin",
            "admin",
            "admin",
            "admin",
            "admin",
            List.of(),
            "admin",
            Boolean.TRUE,
            List.of(),
            List.of(),
            List.of(),
            AvatarEnum.DEFAULT.getName(),
            1L);
            
        try (var fileOutputStream = new FileOutputStream(FILES_USERS_TXT, true)) {
            var objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }*/
}
