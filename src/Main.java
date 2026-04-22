import algorithm.*;
import graph.*;


public class Main {
    public static void main(String[] args) {

        Graph city = new Graph();

        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");

        city.addNode(A);
        city.addNode(B);
        city.addNode(C);
        city.addNode(D);

        Edge edgeAB = new Edge(A, B, 5, 1.2, 0, true);
        
        Edge edgeAC = new Edge(A, C, 10, 1.5, 0, true);
        
        Edge edgeBD = new Edge(B, D, 3, 1.1, 0, true);
        
        Edge edgeCD = new Edge(C, D, 2, 2.0, 0, true);
        
        city.addEdge(edgeAB);
        city.addEdge(edgeAC);
        city.addEdge(edgeBD);
        city.addEdge(edgeCD);

        edgeAB.setClosed(true);
        edgeAC.setAccident(true);

        System.out.println("Shortest travel times from A");

        TrafficManager tm = new TrafficManager();
        tm.setTime(8, city);

        var result = Dijkstra.shortestPath(city, A, false, false);

        for (var entry : result.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}