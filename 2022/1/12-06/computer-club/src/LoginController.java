import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import constants.Constants;
import context.StageContext;
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

    private Boolean isUserFound() throws ClassNotFoundException, IOException {
        try {
            var fileIn = new FileInputStream(Constants.FilesConstants.USERS_FILE);
            var objectIn = new ObjectInputStream(fileIn);
            var keepReading = Boolean.TRUE;
            try {
                while (keepReading) {
                    User user = (User) objectIn.readObject();
                    if (this.user.getText().equalsIgnoreCase(user.getUser())
                            && this.password.getText().equals(user.getPassword())) {
                        objectIn.close();

                        CONTEXT_USER = user;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // var user = new User(
        //         "admin",
        //         "admin",
        //         "UFP31",
        //         "admin",
        //         "admin",
        //         "admin",
        //         "admin",
        //         "admin",
        //         List.of(),
        //         "admin",
        //         Boolean.TRUE,
        //         List.of(),
        //         List.of(),
        //         List.of(),
        //         AvatarEnum.DEFAULT.getName(),
        //         1L);

        // try (var fileOutputStream = new FileOutputStream(Constants.FilesConstants.USERS_FILE, true)) {
        //     var objectOutputStream = new ObjectOutputStream(fileOutputStream);
        //     objectOutputStream.writeObject(user);
        //     objectOutputStream.flush();
        //     objectOutputStream.close();
        // } catch (IOException e) {
        //     System.out.println(e.getMessage());
        //     e.printStackTrace();
        // }

    }
}
