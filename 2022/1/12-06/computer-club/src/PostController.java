import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import context.UserContext;
import entities.Post;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PostController {

    private static final String SCENE_TITLE = "Clubinho da Computação";
    private static final String LAYOUT_VALUE = "layout-menu.fxml";

    private static final String FILES_POSTS_TXT = "src/posts.txt";

    private static final User user = UserContext.getInstance().getUser();

    @FXML
    private TextArea content;

    @FXML
    private ToggleButton isVisible;

    @FXML
    private void addPost(ActionEvent event) {
        var content = this.content.getText();
        if (content.isEmpty() || content.isBlank()) {
            var alert = new Alert(AlertType.WARNING);
            alert.setContentText("Conteúdo não pode ser vazio.");
            alert.show();

            return;
        }

        var post = new Post(content,
                user,
                LocalDate.now(),
                this.isVisible.isSelected());

        try {
            var alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Postagem criada com sucesso!");
            alert.show();
            this.content.setText("");

            var fileOutputStream = new FileOutputStream(FILES_POSTS_TXT, true);
            var objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(post);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            var alert = new Alert(AlertType.ERROR);
            alert.setContentText("Erro ao criar postagem!");
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

}
