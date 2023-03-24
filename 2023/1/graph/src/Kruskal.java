import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Kruskal {

    private final int numberOfNodes;
    private final PriorityQueue<Edge> edges;

    public Kruskal(List<Node> nodes) {
        var edges = convertNodesToGraph(nodes);
        this.edges = new PriorityQueue<>(edges);
        numberOfNodes = nodes.size();
    }

    public List<Edge> applyKruskal() {
        var miniumSpanningTree = new ArrayList<Edge>();
        do {
            var edge = edges.poll();
            var tree = Stream.concat(miniumSpanningTree.stream(), Stream.of(edge))
                    .collect(Collectors.toList());
            resetTree(tree);
            miniumSpanningTree.add(edge);
        } while (miniumSpanningTree.size() < numberOfNodes - 1);

        return miniumSpanningTree;
    }

    private List<Edge> convertNodesToGraph(List<Node> nodes) {
        var edges = new ArrayList<Edge>();

        nodes.forEach(node -> {
            var adjacentNodes = node
                    .getAdjacentNodes()
                    .entrySet()
                    .stream()
                    .collect(Collectors.toList());
                    
            adjacentNodes.forEach(adjacentNode -> {
                var edge = new Edge(node,
                        adjacentNode.getKey(),
                        adjacentNode.getValue());
                edges.add(edge);
            });
        });

        return edges;
    }

    private void resetTree(List<Edge> spanningTree) {
        spanningTree.forEach(edge -> {
            if (Objects.nonNull(edge)) {
                edge.getSource().addAdjacentNode(edge.getDestination(), edge.getWeight());
                edge.getDestination().addAdjacentNode(edge.getSource(), edge.getWeight());
            }
        });
    }
}