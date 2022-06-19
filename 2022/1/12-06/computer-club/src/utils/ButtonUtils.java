package utils;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ButtonUtils {

    public static Button getButton(ActionEvent event) {
        return (Button) event.getSource();
    }

    public static String getButtonText(ActionEvent event) {
        return ((Button) event.getSource()).getText();
    }
}
