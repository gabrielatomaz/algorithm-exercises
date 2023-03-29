import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Kruskal {

    private final int numberOfNodes;
    private final PriorityQueue<Edge> edges;
    private final List<Node> nodes;

    public Kruskal(List<Node> nodes) {
        this.nodes = nodes;
        var edges = convertNodesToEdges(nodes);
        this.edges = new PriorityQueue<>(edges);
        numberOfNodes = nodes.size();
    }

    public List<Edge> applyKruskal() {
        var spanningTree = new ArrayList<Edge>();

        do {
            var edge = edges.poll();

            resetTree(Stream.concat(spanningTree.stream(), Stream.of(edge))
                    .collect(Collectors.toList()));

            if (!hasCycle()) {
                spanningTree.add(edge);
            }
        } while (spanningTree.size() < numberOfNodes - 1);

        return spanningTree;
    }

    private List<Edge> convertNodesToEdges(List<Node> nodes) {
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
            edge.getSource().addAdjacentNode(edge.getDestination());
            edge.getDestination().addAdjacentNode(edge.getSource());
        });
    }

    public boolean hasCycle() {
        for (var node : nodes) {
            if (!node.isVisited() && hasCycle(null, node)) {
                return true;
            }
        }
        
        return false;
    }

    private boolean hasCycle(Node caller, Node current) {
        current.setVisited(true);

        var adjacentNodes = current
                .getAdjacentNodes()
                .entrySet()
                .stream()
                .map(Entry::getKey)
                .collect(Collectors.toList());

        for (var adjacentNode : adjacentNodes) {
            if (adjacentNode.isVisited() && !adjacentNode.equals(caller)
                    || !adjacentNode.isVisited() && hasCycle(current, adjacentNode)) {
                return true;
            }
        }

        return false;
    }
}