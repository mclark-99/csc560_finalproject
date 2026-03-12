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

        city.addEdge(new Edge(A, B, 5, 1.2, 0, true));
        city.addEdge( new Edge(A, C, 10, 1.5, 0, true));
        city.addEdge(new Edge(B, D, 3, 1.1, 0, true));
        city.addEdge(new Edge(C, D, 2, 2.0, 0, true));

        System.out.println("Shortest travel times from A");

        var result = Dijkstra.shortestPath(city, A);

        for (var entry : result.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}