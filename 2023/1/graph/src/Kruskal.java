import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Kruskal {

    private final int numberOfNodes;
    private final List<Node> nodes;
    private final PriorityQueue<Edge> graph;

    public Kruskal(List<Node> nodes) {
        this.nodes = nodes;
        var graph = convertNodesToGraph(nodes);
        this.graph = new PriorityQueue<>(graph);
        numberOfNodes = nodes.size();
    }

    public List<Edge> applyKruskal() {
        var spanningTree = new ArrayList<Edge>();
        do {
            var edge = graph.poll();
            var tree = Stream.concat(spanningTree.stream(), Stream.of(edge))
                    .collect(Collectors.toList());
            resetTree(tree);
            spanningTree.add(edge);
        } while (spanningTree.size() < numberOfNodes - 1);

        return spanningTree;
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
        nodes.forEach(node -> {
            node.setVisited(false);
            node.setAdjacentNodes(new HashMap<>());
        });

        spanningTree.forEach(edge -> {
            if (Objects.nonNull(edge)) {
                edge.getSource().addAdjacentNode(edge.getDestination(), edge.getWeight());
                edge.getDestination().addAdjacentNode(edge.getSource(), edge.getWeight());
            }
        });
    }
}