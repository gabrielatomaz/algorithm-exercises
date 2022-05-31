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

        disableButtons(Boolean.FALSE);

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

        disableButtons(Boolean.FALSE);
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

            disableButtons(Boolean.TRUE);
        }
    }

    private void disableButtons(boolean isEnable) {
        one.setDisable(isEnable);
        two.setDisable(isEnable);
        three.setDisable(isEnable);
        four.setDisable(isEnable);
        five.setDisable(isEnable);
        six.setDisable(isEnable);
        seven.setDisable(isEnable);
        eight.setDisable(isEnable);
        nine.setDisable(isEnable);
    }
}
