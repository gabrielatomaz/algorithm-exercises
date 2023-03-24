import java.text.MessageFormat;

public class Edge implements Comparable<Edge> {
    private Node source;
    private Node destination;
    private int weight;

    public Edge(Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int compareTo(Edge edgeToCompare) {
        return this.weight - edgeToCompare.weight;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} -> {1} ({2})",
                this.source.getName(),
                this.destination.getName(),
                this.weight);
    }
}
