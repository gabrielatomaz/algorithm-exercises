import static java.text.MessageFormat.format;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<List<Node>> adjacentList;
    private List<Edge> edges;

    public Graph(List<Edge> edges) {
        adjacentList = new ArrayList<>();
        this.edges = edges;

        this.edges
        .forEach(edge -> {
            adjacentList.add(new ArrayList<>());
        });
    }

    public void populateNodes() {
        this.edges
        .forEach(edge -> {
            var node = new Node(edge.getDestination(), edge.getWeigth());
            var nodeSource = adjacentList.get(edge.getSource());

            nodeSource.add(node);
        });
    }

    public void print() {
        var sourceVertex = 0;
        var adjacentListSize = this.adjacentList.size();

        while (sourceVertex < adjacentListSize) {
            var adjacentList = this.adjacentList.get(sourceVertex);
            for (Node node : adjacentList) {
                var printMessage = format("Vértice: {0} --> {1} (Peso: {2})\t",
                        sourceVertex, node.getValue(), node.getWeigth());
                System.out.print(printMessage);
            }

            System.out.println();
            sourceVertex++;
        }
    }
}
