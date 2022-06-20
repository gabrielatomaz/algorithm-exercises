import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.AlertUtils;
import utils.ButtonUtils;
import utils.FileUtils;
import utils.StringUtils;
import utils.UserUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import constants.Constants;
import context.StageContext;
import entities.User;
import enums.AvatarEnum;
import enums.RouteEnum;

public class ProfileController extends StageContext implements Initializable {

    private User CONTEXT_USER;

    @FXML
    private TextField address;

    @FXML
    private ImageView avatar;

    @FXML
    private ComboBox<String> avatarOptions;

    @FXML
    private TextField cellPhone;

    @FXML
    private TextField email;

    @FXML
    private TextArea interests;

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
    private void goTo(ActionEvent event) throws IOException {
        goToWithValidations(event);
    }

    @FXML
    private void updateUser(ActionEvent event) {
        var users = FileUtils.getAllUsersFromFile();
        var updatedUsers = new ArrayList<User>();

        users.forEach(user -> {
            if (user.getId().equals(CONTEXT_USER.getId())) {
                setUserFields(user);

                CONTEXT_USER = user;
            }

            updatedUsers.add(user);
        });

        FileUtils.updateUser(updatedUsers, Boolean.TRUE);
    }

    private void setUserFields(User user) {
        user.setName(this.name.getText());
        user.setUser(this.user.getText());
        user.setEmail(this.email.getText());
        user.setPassword(this.password.getText());
        user.setAddress(this.address.getText());
        user.setCellPhone(this.cellPhone.getText());
        user.setTelephone(this.telephone.getText());
        user.setSocialMedia(this.socialMedia.getText());
        user.setStudies(this.studies.getText());
        user.setFollowers(CONTEXT_USER.getFollowers());
        user.setFollowings(CONTEXT_USER.getFollowings());

        var avatarSelected = this.avatarOptions.getSelectionModel().getSelectedItem();
        var avatar = Objects.isNull(avatarSelected) || avatarSelected.isBlank() || avatarSelected.isEmpty()
                ? CONTEXT_USER.getAvatar()
                : avatarSelected;
        user.setAvatar(avatar);

        var interestsList = StringUtils.splitByCommaDelimiter(this.interests.getText());
        var interests = new ArrayList<String>();
        interests.addAll(interestsList);
        user.setInterests(interests);
    }

    private void goToWithValidations(ActionEvent event) throws IOException {
        try {
            var buttonText = ButtonUtils.getButtonText(event);
            var route = RouteEnum.findProfileRouteEnum(buttonText);

            if (RouteEnum.FOLLOWERS.equals(route)) {
                var followers = CONTEXT_USER.getFollowers();
                if (Objects.isNull(followers) || followers.isEmpty()) {
                    var content = Constants.AlertConstants.FOLLOWERS_NOT_FOUND;

                    AlertUtils.setAlert(AlertType.INFORMATION, content);

                    return;
                }
            }

            if (RouteEnum.FOLLOWINGS.equals(route)) {
                var followings = CONTEXT_USER.getFollowings();
                if (Objects.isNull(followings) || followings.isEmpty()) {
                    var content = Constants.AlertConstants.FOLLOWINGS_NOT_FOUND;

                    AlertUtils.setAlert(AlertType.INFORMATION, content);

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
        Arrays.stream(AvatarEnum.values())
                .forEach(avatarEnum -> avatarOptions.getItems().add(avatarEnum.getName()));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var stage = getStage();
                CONTEXT_USER = new UserUtils().getContextUser(stage);

                updateViewFields();
            }

            private void updateViewFields() {
                name.setText(CONTEXT_USER.getName());
                email.setText(CONTEXT_USER.getEmail());
                password.setText(CONTEXT_USER.getPassword());
                address.setText(CONTEXT_USER.getAddress());
                cellPhone.setText(CONTEXT_USER.getCellPhone());
                telephone.setText(CONTEXT_USER.getTelephone());
                user.setText(CONTEXT_USER.getUser());
                socialMedia.setText(CONTEXT_USER.getSocialMedia());
                studies.setText(CONTEXT_USER.getStudies());
                interests.setText(StringUtils.joinWithCommaDelimiter(CONTEXT_USER.getInterests()));

                avatarOptions.setPromptText(CONTEXT_USER.getAvatar());

                updateAvatar(CONTEXT_USER.getAvatar());
            }
        });

    }

    @FXML
    private void changeAvatar(ActionEvent event) {
        updateAvatar(this.avatarOptions.getSelectionModel().getSelectedItem());
    }

    private void updateAvatar(String value) {
        try {
            var avatar = AvatarEnum.findAvatarEnum(value).getPath();
            var image = new Image(getClass().getResourceAsStream(avatar));
            this.avatar.setImage(image);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        goTo(event, RouteEnum.LOGIN, null);
    }
}
