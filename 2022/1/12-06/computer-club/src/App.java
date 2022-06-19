import constants.Constants;
import enums.RouteEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var path = getClass().getResource(RouteEnum.LOGIN.getRoute());
        var fxmlLoader = new FXMLLoader(path);
        var root = (Parent) fxmlLoader.load();
        var scene = new Scene(root);

        primaryStage.setTitle(Constants.ApplicationConstants.STAGE_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
