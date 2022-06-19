package utils;

import entities.User;
import javafx.stage.Stage;

public class UserUtils {
    
    public User getContextUser(Stage stage) {
        return (User) stage.getUserData();
    }
}
