import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

public class Graph {

    private static final String ARROW = " -> ";

    private final List<Node> nodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void printAdjacentNodesAsList() {
        this.nodes.forEach(node -> {
            var adjacentNode = node
                    .getAdjacentNodes()
                    .entrySet()
                    .stream()
                    .map(entry -> format("{0} ({1})", entry.getKey().getName(), entry.getValue()))
                    .collect(Collectors.joining(ARROW));

            System.out.println(format("{0} -> {1}", node.getName(), adjacentNode));
        });
    }

    public void printShortestPaths() {
        this.nodes.forEach(node -> {
            var path = node
                    .getShortestPath()
                    .stream()
                    .map(Node::getName)
                    .map(Objects::toString)
                    .collect(Collectors.joining(ARROW));

            var firstPathPrintMessage = format("{0} : {1}",
                    node.getName(), node.getDistance());
            var nextPathsPrintMessage = format("{0} -> {1} : {2}",
                    path, node.getName(), node.getDistance());

            var printMessage = path.isBlank()
                    ? firstPathPrintMessage
                    : nextPathsPrintMessage;

            System.out.println(printMessage);
        });
    }

    public Node findNodeByName(String name) {
        return this.nodes
                .stream()
                .filter(node -> name.equalsIgnoreCase(node.getName()))
                .findFirst()
                .get();
    }
}
