import java.io.IOException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import utils.AlertUtils;
import utils.FileUtils;
import utils.StringUtils;
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
            AlertUtils.setAlert(AlertType.INFORMATION, Constants.AlertConstants.INVALID_CONTENT);

            return;
        }

        var post = new Post(content,
                CONTEXT_USER,
                LocalDate.now(),
                this.isVisible.isSelected());

        try {
            FileUtils.addPost(post);
            this.content.setText(StringUtils.EMPTY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
