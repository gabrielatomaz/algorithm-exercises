import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicTacToeController {

    private static final String EMPTY_STRING = "";
    private static final String ZERO_VALUE = "0";

    private TicTacToeEnum playerEnum = TicTacToeEnum.X_VALUE;

    private TicTacToeEnum[][] ticTacToeBoard = {
            {
                    TicTacToeEnum.EMPTY, TicTacToeEnum.EMPTY, TicTacToeEnum.EMPTY
            },
            {
                    TicTacToeEnum.EMPTY, TicTacToeEnum.EMPTY, TicTacToeEnum.EMPTY
            },
            {
                    TicTacToeEnum.EMPTY, TicTacToeEnum.EMPTY, TicTacToeEnum.EMPTY
            }
    };

    @FXML
    private Label oScore;

    @FXML
    private Label player;

    @FXML
    private Label xScore;

    @FXML
    private Button eight;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private void clearBoard(ActionEvent event) {
        clearBoard();

        clearPositionButtons();

        enableButtons();

        xScore.setText(ZERO_VALUE);
        oScore.setText(ZERO_VALUE);
    }

    private void clearBoard() {
        for (int i = 0; i < ticTacToeBoard.length; i++) {
            for (int j = 0; j < ticTacToeBoard.length; j++) {
                ticTacToeBoard[i][j] = TicTacToeEnum.EMPTY;
            }
        }
    }

    private void clearPositionButtons() {
        one.setText(EMPTY_STRING);
        two.setText(EMPTY_STRING);
        three.setText(EMPTY_STRING);
        four.setText(EMPTY_STRING);
        five.setText(EMPTY_STRING);
        six.setText(EMPTY_STRING);
        seven.setText(EMPTY_STRING);
        eight.setText(EMPTY_STRING);
        nine.setText(EMPTY_STRING);
    }

    @FXML
    private void defineMove(ActionEvent event) {
        var button = (Button) event.getSource();
        var buttonId = button.getId();

        button.setText(playerEnum.getValue());

        var position = PositionEnum.getPosition(buttonId);
        ticTacToeBoard[position.getRow()][position.getCol()] = playerEnum;

        this.playerEnum = togglePlayer();
        this.player.setText(this.playerEnum.getValue());

        var winner = TicTacToeEnum.searchWinner(ticTacToeBoard);

        updateScore(winner);
    }

    @FXML
    private void playAgain(ActionEvent event) {
        clearBoard();

        clearPositionButtons();
        
        enableButtons();
    }

    private TicTacToeEnum togglePlayer() {
        return this.playerEnum == TicTacToeEnum.X_VALUE
                ? TicTacToeEnum.O_VALUE
                : TicTacToeEnum.X_VALUE;
    }

    private void updateScore(TicTacToeEnum winner) {
        if (!TicTacToeEnum.EMPTY.equals(winner)) {
            if (TicTacToeEnum.O_VALUE.equals(winner)) {
                Integer newScore = Integer.parseInt(oScore.getText()) + 1;
                oScore.setText(newScore.toString());
            } else {
                Integer newScore = Integer.parseInt(xScore.getText()) + 1;
                xScore.setText(newScore.toString());
            }

            disableButtons();
        }
    }

    private void disableButtons() {
        if (EMPTY_STRING.equals(one.getText()))
            one.setDisable(Boolean.TRUE);

        if (EMPTY_STRING.equals(two.getText()))
            two.setDisable(Boolean.TRUE);

        if (EMPTY_STRING.equals(three.getText()))
            three.setDisable(Boolean.TRUE);

        if (EMPTY_STRING.equals(four.getText()))
            four.setDisable(Boolean.TRUE);

        if (EMPTY_STRING.equals(five.getText()))
            five.setDisable(Boolean.TRUE);

        if (EMPTY_STRING.equals(six.getText()))
            six.setDisable(Boolean.TRUE);

        if (EMPTY_STRING.equals(seven.getText()))
            seven.setDisable(Boolean.TRUE);

        if (EMPTY_STRING.equals(eight.getText()))
            eight.setDisable(Boolean.TRUE);

        if (EMPTY_STRING.equals(nine.getText()))
            nine.setDisable(Boolean.TRUE);
    }

    private void enableButtons() {
        one.setDisable(Boolean.FALSE);
        two.setDisable(Boolean.FALSE);
        three.setDisable(Boolean.FALSE);
        four.setDisable(Boolean.FALSE);
        five.setDisable(Boolean.FALSE);
        six.setDisable(Boolean.FALSE);
        seven.setDisable(Boolean.FALSE);
        eight.setDisable(Boolean.FALSE);
        nine.setDisable(Boolean.FALSE);
    }
}
