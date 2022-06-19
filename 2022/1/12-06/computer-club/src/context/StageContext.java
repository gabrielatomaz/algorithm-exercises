package context;

import constants.Constants;
import entities.User;
import enums.RouteEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class StageContext {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void goTo(ActionEvent event, RouteEnum route, User userContext) {
        try {
            var stage = new Stage();
            var path = getClass().getResource(route.getRoute());
            var fxmlLoader = new FXMLLoader(path);

            var root = (Parent) fxmlLoader.load();

            stage.setUserData(userContext);
            stage.setTitle(Constants.ApplicationConstants.STAGE_TITLE);

            var controller = (StageContext) fxmlLoader.getController();
            controller.setStage(stage);

            var scene = new Scene(root);
            stage.setScene(scene);

            stage.show();

            var node = (Node) event.getSource();
            node.getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
