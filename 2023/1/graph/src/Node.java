import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node> {

    private final String name;
    private Integer distance;
    private List<Node> shortestPath;
    private Map<Node, Integer> adjacentNodes;
    private boolean visited;

    public Node(String name) {
        this.name = name;

        distance = Integer.MAX_VALUE;
        
        shortestPath = new LinkedList<>();
        adjacentNodes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public void addAdjacentNode(Node node, int distance) {
        adjacentNodes.put(node, distance);
    }

    public void addAdjacentNode(Node node) {
        adjacentNodes.put(node, null);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }
}
