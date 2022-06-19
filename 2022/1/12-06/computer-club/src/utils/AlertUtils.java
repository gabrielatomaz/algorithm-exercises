package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtils {
    public static void setAlert(AlertType alertType, String content) {
        var alert = new Alert(alertType);
        alert.setContentText(content);
        alert.show();
    }
}
