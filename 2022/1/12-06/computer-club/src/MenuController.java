import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import context.UserContext;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    private static final String SCENE_TITLE = "Clubinho da Computação";

    private final static User user = UserContext.getInstance().getUser();

    @FXML
    private Label loginText;

    @FXML
    private Button createUserButton;

    @FXML
    void goTo(ActionEvent event) throws IOException {
        try {
            var buttonText = getButton(event).getText();

        var route = MenuEnum.findMenuEnum(buttonText).getRoute();

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

    private enum MenuEnum {
        MENU("Menu", "layout-menu.fxml"),
        ADD_USER("Criar usuarios", "layout-add-user.fxml"),
        PROFILE("Perfil", "layout-profile.fxml"),
        FEED("Feed", "layout-feed.fxml"),
        SEARCH("Busca", "layout-search.fxml");


        private String text;
        private String route;

        private MenuEnum(String text, String route) {
            this.text = text;
            this.route = route;
        }

        public String getRoute() {
            return this.route;
        }

        public static MenuEnum findMenuEnum(String text) {
            return Arrays.stream(MenuEnum.values())
            .filter(menu -> menu.text.equals(text))
            .findAny()
            .orElse(MENU);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.createUserButton.setVisible(user.isAdmin());
    }

}

