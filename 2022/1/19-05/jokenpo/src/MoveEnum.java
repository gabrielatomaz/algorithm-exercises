import java.util.Arrays;

public enum MoveEnum {
    ROCK("Pedra") {
        @Override
        public boolean wins(MoveEnum move) {
            return move == MoveEnum.SCISSORS;
        }

        @Override
        public boolean loses(MoveEnum move) {
            return move == MoveEnum.PAPER;
        }
    },
    PAPER("Papel") {
        public boolean wins(MoveEnum move) {
            return move == MoveEnum.ROCK;
        }

        @Override
        public boolean loses(MoveEnum move) {
            return move == MoveEnum.SCISSORS;
        }
    },
    SCISSORS("Tesoura") {
        public boolean wins(MoveEnum move) {
            return move == MoveEnum.PAPER;
        }

        @Override
        public boolean loses(MoveEnum move) {
            return move == MoveEnum.ROCK;
        }
    };

    public abstract boolean wins(MoveEnum move);

    public abstract boolean loses(MoveEnum move);

    private String value;

    private MoveEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static MoveEnum findMove(String move) {
        return Arrays.stream(MoveEnum.values())
        .filter(moveEnum -> moveEnum.value.equals(move))
        .findAny()
        .get();
    }
}