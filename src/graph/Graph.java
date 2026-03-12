package graph;

import java.util.*;

public class Graph {
    
    private Map<Node, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(Node node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Edge edge) {
        adjacencyList.get(edge.getSource()).add(edge);

        if (edge.isBidirectional()) {
            Edge reverse = new Edge(
                edge.getDestination(),
                edge.getSource(),
                edge.getTravelTime(),
                1,
                edge.getToll(),
                true
);
            
            adjacencyList.get(edge.getDestination()).add(reverse);
        }
    }

    public List<Edge> getEdges(Node node) {
        return adjacencyList.get(node);
    }

    public Set<Node> getNodes() {
        return adjacencyList.keySet();
    }
}
