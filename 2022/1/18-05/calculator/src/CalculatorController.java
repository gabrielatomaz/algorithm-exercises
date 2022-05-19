import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    private static final String STRING_EMPTY = "";
    private static final String ZERO_VALUE = "0";
    private static final String REGEX = "\\";

    @FXML
    private Button division;

    @FXML
    private Button minus;

    @FXML
    private Button plus;

    @FXML
    private Label result;

    @FXML
    private Button times;

    private String currentLabel = ZERO_VALUE;

    private MathSignEnum mathSignEnum;

    @FXML
    private void clear(ActionEvent event) {
        this.currentLabel = ZERO_VALUE;
        this.result.setText(currentLabel);
        enableMathButtons();
    }

    @FXML
    void setResult(ActionEvent event) {
        enableMathButtons();
        this.result.setText(getResult());
    }

    @FXML
    private void setEquation(ActionEvent event) {
        try {
            var text = this.getButtonText(event);

            this.currentLabel = this.currentLabel.equals(ZERO_VALUE)
                    ? STRING_EMPTY
                    : this.currentLabel;

            if (isAMathSign(text)) {
                disableMathButtons();
                mathSignEnum = MathSignEnum.findMathSingEnum(text);
                this.currentLabel += mathSignEnum.getValue();
            } else
                this.currentLabel += text;

            this.result.setText(currentLabel);
        } catch (Exception e) {
            clear(event);
        }
    }

    private String getButtonText(ActionEvent event) {
        var button = (Button) event.getSource();
        var text = button.getText();

        return text;
    }

    private Boolean isAMathSign(String text) {
        return text.equalsIgnoreCase(MathSignEnum.DIVISION.getValue())
                || text.equalsIgnoreCase(MathSignEnum.MINUS.getValue())
                || text.equalsIgnoreCase(MathSignEnum.PLUS.getValue())
                || text.equalsIgnoreCase(MathSignEnum.TIMES.getValue());
    }

    private void disableMathButtons() {
        times.setDisable(Boolean.TRUE);
        division.setDisable(Boolean.TRUE);
        plus.setDisable(Boolean.TRUE);
        minus.setDisable(Boolean.TRUE);
    }

    private void enableMathButtons() {
        times.setDisable(Boolean.FALSE);
        division.setDisable(Boolean.FALSE);
        plus.setDisable(Boolean.FALSE);
        minus.setDisable(Boolean.FALSE);
    }

    public String getResult() {
        var values = this.currentLabel.split(
                REGEX.concat(mathSignEnum.getValue()));

        if (values.length == 2)
            this.currentLabel = mathSignEnum.doMath(values);

        return this.currentLabel;
    }
}
