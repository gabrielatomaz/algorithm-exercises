import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    private static final String STRING_EMPTY = "";
    private static final String ZERO_VALUE = "0";
    private static final String REGEX = "\\";

    @FXML
    private Button clear;

    @FXML
    private Button dot;

    @FXML
    private Label result;

    private String currentLabel = ZERO_VALUE;

    @FXML
    private void clear(ActionEvent event) {
        this.result.setText(ZERO_VALUE);
    }

    @FXML
    private void setEquation(ActionEvent event) {
        var text = this.getButtonText(event);

        this.currentLabel = this.currentLabel.equals(ZERO_VALUE)
                ? STRING_EMPTY
                : this.currentLabel;

        this.currentLabel += text;

        if (isAMathSign(text)) {
            var mathSign = MathSignEnum.findMathSingEnum(text);
            var values = this.currentLabel.split(REGEX.concat(mathSign.getValue()));

            if (values.length == 2)
                this.currentLabel = mathSign.doMath(values);
        }

        this.result.setText(currentLabel);
    }

    private String getButtonText(ActionEvent event) {
        var button = (Button) event.getSource();
        var text = button.getText();

        return text;
    }

    private Boolean isAMathSign(String text) {
        return text.equals(MathSignEnum.DIVISION.getValue()) ||
                text.equals(MathSignEnum.MINUS.getValue())
                || text.equals(MathSignEnum.PLUS.getValue())
                || text.equals(MathSignEnum.TIMES.getValue());
    }
}
