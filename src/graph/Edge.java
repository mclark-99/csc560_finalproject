package graph;

public class Edge {

    private Node source;
    private Node destination;

    private double distance;
    private double congestionFactor;
    private double toll;

    private boolean bidirectional;

    public Edge(Node source, Node destination,
            double distance,
            double congestionFactor,
            double toll,
            boolean bidirectional) {
    
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.congestionFactor = congestionFactor;
        this.toll = toll;
        this.bidirectional = bidirectional;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public double getTravelTime () {
        return distance * congestionFactor;
    }

    public double getToll() {
        return toll;
    }

    public boolean isBidirectional() {
        return bidirectional;
    }   
}
