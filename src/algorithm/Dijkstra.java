package algorithm;

import graph.*;
import java.util.*;

public class Dijkstra {
    
    public static Map<Node, Double> shortestPath(Graph graph, Node start) {
        
        Map<Node, Double> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Node node : graph.getNodes()) {
            distances.put(node, Double.MAX_VALUE);
        }

        distances.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            for (Edge edge : graph.getEdges(current)) {
                
                Node neighbor = edge.getDestination();

                double newDistance =
                    distances.get(current) + edge.getTravelTime();
                
                if (newDistance < distances.get(neighbor)) {
                    
                    distances.put(neighbor, newDistance);
                    pq.add(neighbor);
                }
            }
        }
        return distances;
    }
}