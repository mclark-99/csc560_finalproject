package algorithm;
import graph.*;

public class TrafficManager {

    private int currentHour;

    public void setTime(int hour, Graph graph) {
        this.currentHour = hour;
        updateCongestion(graph);
    }

    private void updateCongestion(Graph graph) {
        for (Node node : graph.getNodes()) {

            var edges = graph.getEdges(node);
            if (edges == null) continue;

            for (Edge edge : edges) {

                if (currentHour >= 7 && currentHour <= 9) {
                    edge.setCongestionFactor(2.0);
                } 
                else if (currentHour >= 16 && currentHour <= 18) {
                    edge.setCongestionFactor(2.5);
                } 
                else {
                    edge.setCongestionFactor(1.0);
                }
            }
        }
    }
}