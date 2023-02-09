public class Edge {
    private int source,
        destination, 
        weigth;

    public Edge(int source, int destination, int weigth) {
        this.source = source;
        this.destination= destination;
        this.weigth = weigth;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return this.destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getWeigth() {
        return this.weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }
}
