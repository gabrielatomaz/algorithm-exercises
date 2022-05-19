
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class JokenpoController implements Initializable {

    private static final String PLAY_AGAIN_TEXT = "Jogar novamente",
            YOU_TEXT = "Você",
            COMPUTER_TEXT = "Computador",
            WINNER_TEXT = "{0} ganhou!",
            BOARD_TEXT = "Computador: {0}\n Você: {1}\n",
            TIE_TEXT = "Empate!";

    @FXML
    private Button paper;

    @FXML
    private Button playButton;

    @FXML
    private Label result;

    @FXML
    private Button rock;

    @FXML
    private Button scissor;

    private MoveEnum move;

    private Boolean toggle = Boolean.FALSE;

    @FXML
    private void chooseMove(ActionEvent event) {
        var move = getButtonText(event);
        this.move = MoveEnum.findMove(move);
        toggleMoves();
        this.playButton.setDisable(Boolean.FALSE);
    }

    @FXML
    private void play(ActionEvent event) {
        var result = jokenpo(this.move);
        this.result.setText(result);
        this.playButton.setText(PLAY_AGAIN_TEXT);
        toggleMoves();
    }

    private String getButtonText(ActionEvent event) {
        var button = (Button) event.getSource();
        var text = button.getText();

        return text;
    }

    private String jokenpo(MoveEnum move) {
        var computerMove = MoveEnum.values()[(int) (Math.random() * MoveEnum.values().length)];

        var message = MessageFormat.format(BOARD_TEXT,
                computerMove.getValue(), move.getValue());

        if (move.wins(computerMove))
            return MessageFormat.format(message.concat(WINNER_TEXT), YOU_TEXT);
        else if (move.loses(computerMove))
            return MessageFormat.format(message.concat(WINNER_TEXT), COMPUTER_TEXT);
        else
            return message.concat(TIE_TEXT);
    }

    private void toggleMoves() {
        this.toggle = !toggle;
        this.paper.setDisable(this.toggle);
        this.rock.setDisable(this.toggle);
        this.scissor.setDisable(this.toggle);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.playButton.setDisable(Boolean.TRUE);
    }
}
