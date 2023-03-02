import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.text.MessageFormat.format;;

public class Dijkstra {

    private static final String ARROW = " -> ";

    public void calculateShortestPath(Node source) {
        source.setDistance(0);

        var sourceNodeSet = Collections.singleton(source);

        var settledNodes = new HashSet<Node>();
        var unsettledNodes = new PriorityQueue<Node>(sourceNodeSet);

        while (!unsettledNodes.isEmpty()) {
            var currentNode = unsettledNodes.poll();
            var adjacentNodesEntriesStream = currentNode
                    .getAdjacentNodes()
                    .entrySet()
                    .stream();

            adjacentNodesEntriesStream
                    .filter(entry -> settledNodesContainsNode(settledNodes, entry.getKey()))
                    .forEach(entry -> {
                        var node = entry.getKey();
                        var distance = entry.getValue();

                        evaluateDistanceAndPath(node, distance, currentNode);

                        unsettledNodes.add(entry.getKey());
                    });

            settledNodes.add(currentNode);
        }
    }

    private boolean settledNodesContainsNode(HashSet<Node> settledNodes,
            Node node) {
        return !settledNodes.contains(node);
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

    public void printPaths(List<Node> nodes) {
        nodes.forEach(node -> {
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
}