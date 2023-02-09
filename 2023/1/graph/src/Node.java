public class Node {
    private int value,
        weigth;

    public Node(int value, int weigth) {
        this.value = value;
        this.weigth = weigth;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeigth() {
        return this.weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

}
