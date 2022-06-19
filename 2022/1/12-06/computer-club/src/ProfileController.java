import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import context.UserContext;
import entities.User;
import enums.AvatarEnum;

public class ProfileController implements Initializable {

    private static final String SCENE_TITLE = "Clubinho da Computação";
    private static final String LAYOUT_VALUE = "layout.fxml";

    private static final User USER = UserContext.getInstance().getUser();

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
        createScene(event);
    }

    @FXML
    private void updateUser(ActionEvent event) {

    }

    private void createScene(ActionEvent event) throws IOException {
        try {
            var buttonText = getButton(event).getText();
            var route = ProfileRouteEnum.findProfileRouteEnum(buttonText).getRoute();

            if (ProfileRouteEnum.FOLLOWERS.getRoute().equals(route)
                    || ProfileRouteEnum.FOLLOWINGS.getRoute().equals(route)) {
                var followers = USER.getFollowers();
                var followings = USER.getFollowings();
                if (Objects.isNull(followers) || Objects.isNull(followings)) {
                    var contentText = ProfileRouteEnum.FOLLOWERS.getRoute().equals(route)
                            ? "Você não possui nenhum seguidor!"
                            : "Você não segue nenhum usuário!";

                    var alert = new Alert(AlertType.WARNING);
                    alert.setContentText(contentText);
                    alert.show();

                    return;
                }
            }

            var stage = new Stage();
            var path = getClass().getResource(route);
            var fxmlLoader = new FXMLLoader(path);
            var root = (Parent) fxmlLoader.load();
            var scene = new Scene(root);

            stage.setTitle(SCENE_TITLE);
            stage.setScene(scene);
            stage.show();

            var node = (Node) event.getSource();
            node.getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Button getButton(ActionEvent event) {
        return (Button) event.getSource();
    }

    private enum ProfileRouteEnum {
        FOLLOWERS("Seguidores", "layout-followers.fxml"),
        FOLLOWINGS("Seguidos", "layout-followings.fxml"),
        ADD_POST("Criar post", "layout-add-post.fxml"),
        MENU("Voltar para o menu", "layout-menu.fxml");

        private String text;
        private String route;

        private ProfileRouteEnum(String text, String route) {
            this.text = text;
            this.route = route;
        }

        public String getRoute() {
            return this.route;
        }

        public static ProfileRouteEnum findProfileRouteEnum(String text) {
            return Arrays.stream(ProfileRouteEnum.values())
                    .filter(route -> route.text.equals(text))
                    .findAny()
                    .orElse(MENU);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Arrays.stream(AvatarEnum.values())
                .forEach(avatarEnum -> avatarOptions.getItems().add(avatarEnum.getName()));

        this.name.setText(USER.getName());
        this.email.setText(USER.getEmail());
        this.password.setText(USER.getPassword());
        this.address.setText(USER.getAddress());
        this.cellPhone.setText(USER.getCellPhone());
        this.telephone.setText(USER.getTelephone());
        this.user.setText(USER.getUser());
        this.socialMedia.setText(USER.getSocialMedia());
        this.studies.setText(USER.getStudies());
        this.interests.setText(USER.getInterests().stream().map(Object::toString)
                .collect(Collectors.joining(", ")));

        updateAvatar(USER.getAvatar());
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
        UserContext.getInstance().setUser(null);

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

}
