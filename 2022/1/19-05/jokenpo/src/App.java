import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static final String LAYOUT_VALUE = "layout.fxml";
    private static final String SCENE_TITLE = "Calculadora";

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        var path = getClass().getResource(LAYOUT_VALUE);
        var fxmlLoader = new FXMLLoader(path);
        var root = (Parent) fxmlLoader.load();
        var scene = new Scene(root);

        primaryStage.setTitle(SCENE_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
