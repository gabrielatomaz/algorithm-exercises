import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import entities.User;
import enums.SearchOptionsEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SearchController implements Initializable {

    private static final String FILES_USERS_TXT = "src/users.txt";
    private static final String LAYOUT_VALUE = "layout-menu.fxml";
    private static final String SCENE_TITLE = "Clubinho da Computação";

    @FXML
    private ListView<String> users;

    private ArrayList<User> usersList = new ArrayList<>();

    @FXML
    private ComboBox<String> searchOptions;

    @FXML
    private TextField searchBy;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Arrays.stream(SearchOptionsEnum.values())
                .forEach(option -> this.searchOptions.getItems().add(option.getName()));
        try {
            var fileIn = new FileInputStream(FILES_USERS_TXT);
            var objectIn = new ObjectInputStream(fileIn);
            var keepReading = Boolean.TRUE;
            try {
                while (keepReading) {
                    var user = (User) objectIn.readObject();

                    usersList.add(user);

                    var interests = Objects.isNull(user.getInterests())
                            ? ""
                            : user.getInterests().stream().map(Object::toString)
                                    .collect(Collectors.joining(", "));

                    var userView = MessageFormat.format("{0} - Nome: {1} \n Interesses: {2}", user.getId(), user.getName(), interests);
                    this.users.getItems().add(userView);

                    objectIn = new ObjectInputStream(fileIn);
                }

                objectIn.close();
            } catch (EOFException e) {
                keepReading = false;
                objectIn.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void search(ActionEvent event) {
        var searchBy = this.searchBy.getText();
        var searchOption = this.searchOptions.getSelectionModel().getSelectedItem();

        var searchOptionEnum = SearchOptionsEnum.findSearchOptionsEnum(searchOption);

        var usersFound = this.usersList.stream()
                .filter(searchOptionEnum.searchBy(searchBy))
                .map(user -> {
                    var interests = Objects.isNull(user.getInterests())
                            ? ""
                            : user.getInterests().stream().map(Object::toString)
                                    .collect(Collectors.joining(", "));

                    return MessageFormat.format("{0} - Nome: {1} \n Interesses: {2}", user.getId(), user.getName(), interests);
                })
                .collect(Collectors.toList());

        if (usersFound.isEmpty()) {
            var alert = new Alert(AlertType.WARNING);
            alert.setContentText("Nenhum usuário encontrado!");
            alert.show();

            return;
        }

        this.users.getItems().clear();

        usersFound.forEach(userView -> this.users.getItems().add(userView));
    }

    @FXML
    private void clearSearch(ActionEvent event) {
        this.users.getItems().clear();
        this.usersList.forEach(user -> {
            var interests = Objects.isNull(user.getInterests())
                            ? ""
                            : user.getInterests().stream().map(Object::toString)
                                    .collect(Collectors.joining(", "));

            var userView = MessageFormat.format("{0} - Nome: {1} \n Interesses: {2}", user.getId(), user.getName(), interests);

            this.users.getItems().add(userView);
        });
    }

    @FXML
    private void followUser(ActionEvent event) {
        var user = this.users.getSelectionModel().getSelectedItem();
        var userId = user.split("-")[0].trim();
        System.out.println(userId);
    }


}
