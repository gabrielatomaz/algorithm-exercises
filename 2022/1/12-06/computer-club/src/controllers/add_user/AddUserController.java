package controllers.add_user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import constants.Constants;
import context.StageContext;
import entities.Post;
import entities.User;
import enums.AvatarEnum;
import enums.RouteEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import utils.AlertUtils;
import utils.FileUtils;
import utils.StringUtils;
import utils.UserUtils;

public class AddUserController extends StageContext implements Initializable {

    private User CONTEXT_USER;

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
        var interestsList = StringUtils.splitByCommaDelimiter(this.interests.getText());
        var interests = new ArrayList<String>();
        interests.addAll(interestsList);

        if (hasEmptyField(this.name.getText(),
                this.user.getText(),
                this.password.getText(),
                this.email.getText(),
                this.address.getText(),
                this.cellPhone.getText(),
                this.telephone.getText(),
                this.socialMedia.getText(),
                this.studies.getText())) {
            AlertUtils.setAlert(AlertType.WARNING, Constants.AlertConstants.FIELDS_CANNOT_BE_EMPTY);

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
                new ArrayList<Post>(),
                new ArrayList<User>(),
                new ArrayList<User>(),
                AvatarEnum.DEFAULT.getName(),
                FileUtils.getNexUserId());
        try {
            FileUtils.addUser(user);

            emptyFields();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void emptyFields() {
        this.name.setText(StringUtils.EMPTY);
        this.user.setText(StringUtils.EMPTY);
        this.password.setText(StringUtils.EMPTY);
        this.email.setText(StringUtils.EMPTY);
        this.address.setText(StringUtils.EMPTY);
        this.cellPhone.setText(StringUtils.EMPTY);
        this.telephone.setText(StringUtils.EMPTY);
        this.socialMedia.setText(StringUtils.EMPTY);
        this.interests.setText(StringUtils.EMPTY);
        this.studies.setText(StringUtils.EMPTY);
        this.isAdmin.setSelected(Boolean.FALSE);
    }

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        goTo(event, RouteEnum.MENU, CONTEXT_USER);
    }

    private Boolean hasEmptyField(String... fields) {
        return Arrays.stream(fields)
                .filter(field -> field.isBlank() || field.isEmpty())
                .count() > 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var stage = getStage();
                CONTEXT_USER = new UserUtils().getContextUser(stage);
            }
        });
    }
}
