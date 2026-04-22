package algorithm;

import graph.*;
import java.util.*;

public class Dijkstra {
    
    public static Map<Node, Double> shortestPath(
    Graph graph,
    Node start,
    boolean avoidTolls,
    boolean emergencyMode
    ) {
        
        Set<Node> visited = new HashSet<>();
        Map<Node, Double> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Node node : graph.getNodes()) {
            distances.put(node, Double.MAX_VALUE);
        }

        distances.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            if (visited.contains(current)) continue;
            visited.add(current); 

            List<Edge> edges = graph.getEdges(current);
            if (edges ==null) continue;
            
            for (Edge edge : edges) {
                
                Node neighbor = edge.getDestination();

                if (!emergencyMode && edge.isClosed()) continue;

                if (avoidTolls && edge.getToll() > 0) continue;


                double newDistance = distances.get(current) + edge.getTravelTime();
                
                if (newDistance < distances.get(neighbor)) {
                    
                    distances.put(neighbor, newDistance);
                    pq.add(neighbor);
                }
            }
        }
        return distances;
    }
}