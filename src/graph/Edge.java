package graph;

public class Edge {

    private Node source;
    private Node destination;

    private double distance;
    private double congestionFactor;
    private double toll;

    private boolean bidirectional;
    private boolean isClosed;
    private boolean hasAccident;

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
        this.isClosed = false;
        this.hasAccident = false;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public double getTravelTime () {
        
        if (isClosed) return Double.MAX_VALUE;
        double factor = congestionFactor;

        if (hasAccident) {
            factor *= 2.0;
        }

        return distance * factor;
    }

    public double getToll() {
        return toll;
    }

    public boolean isBidirectional() {
        return bidirectional;
    }   

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        this.isClosed = closed;
    }

    public boolean hasAccident() {
        return hasAccident;
    }

    public void setAccident(boolean accident) {
        this.hasAccident = accident;
    }

    public void setCongestionFactor(double factor) {
    this.congestionFactor = factor;
}
}
