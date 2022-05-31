public enum TicTacToeEnum {
    X_VALUE("X"),
    O_VALUE("O"),
    EMPTY("");

    private String value;

    private TicTacToeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static TicTacToeEnum searchWinner(TicTacToeEnum[][] board) {
        var ticTacToeEnum = EMPTY;
        for (int row = 0; row < 3; row++) {
            var winnerX = board[row][0] == X_VALUE
                    && board[row][1] == X_VALUE
                    && board[row][2] == X_VALUE;

            var winnerO = board[row][0] == O_VALUE
                    && board[row][1] == O_VALUE
                    && board[row][2] == O_VALUE;

            if (winnerX) {
                ticTacToeEnum = X_VALUE;
                break;
            }

            if (winnerO) {
                ticTacToeEnum = O_VALUE;
                break;
            }
        }

        for (int col = 0; col < 3; col++) {
            var winnerX = board[0][col] == X_VALUE
                    && board[1][col] == X_VALUE
                    && board[2][col] == X_VALUE;

            var winnerO = board[0][col] == O_VALUE
                    && board[1][col] == O_VALUE
                    && board[2][col] == O_VALUE;

            if (winnerX) {
                ticTacToeEnum = X_VALUE;
                break;
            }

            if (winnerO) {
                ticTacToeEnum = O_VALUE;
                break;
            }
        }

        var principalDiagonal = board[0][0] == board[1][1]
                && board[1][1] == board[2][2];
        var secondaryDiagonal = board[0][2] == board[1][1]
                && board[1][1] == board[2][0];

        if (principalDiagonal)
            ticTacToeEnum = board[0][0];
        else if (secondaryDiagonal)
            ticTacToeEnum = board[0][2];

        return ticTacToeEnum;
    }
}
