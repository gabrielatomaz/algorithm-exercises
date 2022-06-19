import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.UserUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
        var users = findUsers();
        var updatedUsers = new ArrayList<User>();

        users.forEach(user -> {
            if (user.getId().equals(CONTEXT_USER.getId())) {

                user.setName(this.name.getText());
                user.setUser(this.user.getText());
                user.setEmail(this.email.getText());
                user.setPassword(this.password.getText());
                user.setAddress(this.address.getText());
                user.setCellPhone(this.cellPhone.getText());
                user.setTelephone(this.telephone.getText());
                user.setSocialMedia(this.socialMedia.getText());
                user.setStudies(this.studies.getText());
                user.setAvatar(this.avatarOptions.getSelectionModel().getSelectedItem());

                var interests = Arrays.asList(this.interests.getText().split(","));
                user.setInterests(interests);

                CONTEXT_USER = user;
            }

            updatedUsers.add(user);
        });

        Boolean isFirst = Boolean.FALSE;
        try {
            for (User user : updatedUsers) {
                var fileOutputStream = new FileOutputStream(Constants.FilesConstants.USERS_FILE, isFirst);

                var objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(user);
                objectOutputStream.flush();
                objectOutputStream.close();

                if (!isFirst)
                    isFirst = Boolean.TRUE;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void goToWithValidations(ActionEvent event) throws IOException {
        try {
            var buttonText = getButton(event).getText();
            var route = RouteEnum.findProfileRouteEnum(buttonText);

            if (RouteEnum.FOLLOWERS.equals(route)
                    || RouteEnum.FOLLOWINGS.equals(route)) {
                var followers = CONTEXT_USER.getFollowers();
                var followings = CONTEXT_USER.getFollowings();
                if (Objects.isNull(followers) || Objects.isNull(followings)
                        || followers.isEmpty() || followings.isEmpty()) {
                    var contentText = RouteEnum.FOLLOWERS.equals(route)
                            ? "Você não possui nenhum seguidor!"
                            : "Você não segue nenhum usuário!";

                    var alert = new Alert(AlertType.WARNING);
                    alert.setContentText(contentText);
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
        Arrays.stream(AvatarEnum.values())
                .forEach(avatarEnum -> avatarOptions.getItems().add(avatarEnum.getName()));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                var stage = getStage();
                CONTEXT_USER = new UserUtils().getContextUser(stage);

                name.setText(CONTEXT_USER.getName());
                email.setText(CONTEXT_USER.getEmail());
                password.setText(CONTEXT_USER.getPassword());
                address.setText(CONTEXT_USER.getAddress());
                cellPhone.setText(CONTEXT_USER.getCellPhone());
                telephone.setText(CONTEXT_USER.getTelephone());
                user.setText(CONTEXT_USER.getUser());
                socialMedia.setText(CONTEXT_USER.getSocialMedia());
                studies.setText(CONTEXT_USER.getStudies());
                interests.setText(CONTEXT_USER.getInterests().stream().map(Object::toString)
                        .collect(Collectors.joining(", ")));

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

    private List<User> findUsers() {
        var users = new ArrayList<User>();
        try {
            var fileIn = new FileInputStream(Constants.FilesConstants.USERS_FILE);
            var objectIn = new ObjectInputStream(fileIn);
            var keepReading = Boolean.TRUE;
            try {
                while (keepReading) {
                    User user = (User) objectIn.readObject();

                    users.add(user);

                    objectIn = new ObjectInputStream(fileIn);
                }

                objectIn.close();
            } catch (EOFException e) {
                keepReading = false;
                objectIn.close();

                return users;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return users;
    }

}
