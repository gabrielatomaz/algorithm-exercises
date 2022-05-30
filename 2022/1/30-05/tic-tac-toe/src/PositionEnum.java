import java.util.Arrays;

public enum PositionEnum {
    ONE(0, 0),
    TWO(0, 1),
    THREE(0, 2),
    FOUR(1, 0),
    FIVE(1, 1),
    SIX(1, 2),
    SEVEN(2, 0),
    EIGHT(2, 1),
    NINE(2, 2);

    private int row, col;

    private PositionEnum(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public static PositionEnum getPosition(String position) {
        return Arrays.stream(PositionEnum.values())
                .filter(positionEnum -> positionEnum.name()
                        .equals(position.toUpperCase()))
                .findFirst()
                .get();
    }
}
