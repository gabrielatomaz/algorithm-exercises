import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import context.UserContext;
import entities.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class FollowingsController implements Initializable {

    private static final String LAYOUT_VALUE = "layout-menu.fxml";
    private static final String SCENE_TITLE = "Clubinho da Computação";

    private static final User user = UserContext.getInstance().getUser();

    @FXML
    private ListView<User> followings;

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

    @FXML
    private void unfollow(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var followings = FXCollections.observableArrayList(
                this.user.getFollowings());

        for (User user : followings) {
            this.followings.getItems().add(user);
        }
    }

}
