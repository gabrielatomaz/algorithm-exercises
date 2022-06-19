import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import entities.User;
import enums.AvatarEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddUserController {

    private static final String LAYOUT_VALUE = "layout-menu.fxml";
    private static final String SCENE_TITLE = "Clubinho da Computação";
    private static final String FILES_USERS_TXT = "src/users.txt";

    @FXML
    private TextField address;

    @FXML
    private TextField cellPhone;

    @FXML
    private TextField email;

    @FXML
    private TextArea interests;

    @FXML
    private ToggleButton isAdmin;

    @FXML
    private Label loginText;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField socialMedia;

    @FXML
    private TextField studies;

    @FXML
    private TextField telephone;

    @FXML
    private TextField user;

    @FXML
    private void createUser(ActionEvent event) throws IOException {
        var interests = Arrays.asList(this.interests.getText().split(","));
        if (hasEmptyField(this.name.getText(),
                this.user.getText(),
                this.password.getText(),
                this.email.getText(),
                this.address.getText(),
                this.cellPhone.getText(),
                this.telephone.getText(),
                this.socialMedia.getText(),
                this.studies.getText())) {
            var alert = new Alert(AlertType.WARNING);
            alert.setContentText("Campos de texto não devem estar em branco!");
            alert.show();

            return;
        }

        var user = new User(
                this.name.getText(),
                this.user.getText(),
                this.password.getText(),
                this.email.getText(),
                this.address.getText(),
                this.cellPhone.getText(),
                this.telephone.getText(),
                this.socialMedia.getText(),
                interests,
                this.studies.getText(),
                this.isAdmin.isSelected(),
                List.of(),
                List.of(),
                List.of(),
                AvatarEnum.DEFAULT.getName(),
                getNextId());
        try {
            var alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Usuário criado com sucesso!");
            alert.show();
            this.name.setText("");
            this.user.setText("");
            this.password.setText("");
            this.email.setText("");
            this.address.setText("");
            this.cellPhone.setText("");
            this.telephone.setText("");
            this.socialMedia.setText("");
            this.interests.setText("");
            this.studies.setText("");
            this.isAdmin.setSelected(Boolean.FALSE);

            var fileOutputStream = new FileOutputStream(FILES_USERS_TXT, true);
            var objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            var alert = new Alert(AlertType.ERROR);
            alert.setContentText("Erro ao criar usuário!");
            alert.show();
        }
    }

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
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
    }

    private Boolean hasEmptyField(String... fields) {
        return Arrays.stream(fields)
                .filter(field -> field.isBlank() || field.isEmpty())
                .count() > 0;
    }

    private Long getNextId() {
        Long idCount = 1L;
        try {
            var fileIn = new FileInputStream(FILES_USERS_TXT);
            var objectIn = new ObjectInputStream(fileIn);
            var keepReading = Boolean.TRUE;
            try {
                while (keepReading) {
                    idCount += 1L;
                    objectIn.readObject();
                    objectIn = new ObjectInputStream(fileIn);
                }
                
                objectIn.close();

                return idCount;
            } catch (EOFException e) {
                keepReading = false;
                objectIn.close();

                return idCount;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return idCount;
    }

}
