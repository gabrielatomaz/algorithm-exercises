import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import utils.AlertUtils;
import utils.FileUtils;
import utils.ObjectUtils;
import utils.UserUtils;

public class FeedController extends StageContext implements Initializable {

    private User CONTEXT_USER;

    @FXML
    private ListView<String> posts;

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        goTo(event, RouteEnum.MENU, CONTEXT_USER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    var stage = getStage();
                    CONTEXT_USER = new UserUtils().getContextUser(stage);

                    var followings = CONTEXT_USER.getFollowings();

                    if (ObjectUtils.isListValid(followings)) {
                        for (User user : followings) {
                            var userPosts = FileUtils.findPostUserById(user.getId());

                            if (ObjectUtils.isListValid(userPosts)) {
                                for (Post post : userPosts) {
                                    var autor = post.getAuthor();
                                    var postView = MessageFormat.format(Constants.ViewConstants.POST_STRUCTURE,
                                            autor.getName(), autor.getUser(), post.getTimestamp().toString(),
                                            post.getContent());

                                    if (post.isVisible())
                                        posts.getItems().add(postView);
                                }
                            }
                        }
                    }

                    if (!ObjectUtils.isListValid(posts.getItems()))
                        AlertUtils.setAlert(AlertType.INFORMATION, Constants.AlertConstants.POSTS_NOT_FOUND);
                } catch (Exception e) {
                    AlertUtils.setAlert(AlertType.INFORMATION, Constants.AlertConstants.POSTS_NOT_FOUND);
                }
            }
        });
    }
}
