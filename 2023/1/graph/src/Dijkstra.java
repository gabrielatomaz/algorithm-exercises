import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra {

    public void calculateShortestPath(Node source) {
        source.setDistance(0);

        var sourceNodeSet = Collections.singleton(source);

        var closedNodes = new HashSet<Node>();
        var openNodes = new PriorityQueue<Node>(sourceNodeSet);

        while (!openNodes.isEmpty()) {
            var currentNode = openNodes.poll();
            var adjacentNodesEntriesStream = currentNode
                    .getAdjacentNodes()
                    .entrySet()
                    .stream();

            adjacentNodesEntriesStream
                    .filter(entry -> closedNodesNotContainsNode(closedNodes, entry.getKey()))
                    .forEach(entry -> {
                        var node = entry.getKey();
                        var distance = entry.getValue();

                        evaluateDistanceAndPath(node, distance, currentNode);

                        openNodes.add(entry.getKey());
                    });

            closedNodes.add(currentNode);
        }
    }

    private boolean closedNodesNotContainsNode(HashSet<Node> closedNodes,
            Node node) {
        return !closedNodes.contains(node);
    }

    private void evaluateDistanceAndPath(Node adjacentNode,
            Integer disance,
            Node sourceNode) {
        var newDistance = sourceNode.getDistance() + disance;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);

            var shortestPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(shortestPath);
        }
    }
}