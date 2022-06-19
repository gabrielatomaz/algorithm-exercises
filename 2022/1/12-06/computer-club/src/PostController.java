import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import constants.Constants;
import context.StageContext;
import entities.Post;
import entities.User;
import enums.RouteEnum;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import utils.UserUtils;

public class PostController extends StageContext implements Initializable {

    private User CONTEXT_USER;

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
                CONTEXT_USER,
                LocalDate.now(),
                this.isVisible.isSelected());

        try {
            var alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Postagem criada com sucesso!");
            alert.show();
            this.content.setText("");

            var fileOutputStream = new FileOutputStream(Constants.FilesConstants.POSTS_FILE, true);
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
        goTo(event, RouteEnum.MENU, CONTEXT_USER);
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
