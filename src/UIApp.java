import algorithm.*;
import graph.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class UIApp extends Application {

    private Graph city;
    private Map<String, Node> nodes = new HashMap<>();
    private Map<String, Edge> edges = new HashMap<>();

    private Pane graphPane = new Pane();
    private Label output = new Label("Output");

    private boolean avoidTolls = false;
    private boolean emergencyMode = false;

    private TrafficManager tm = new TrafficManager();

    @Override
    public void start(Stage stage) {

        setupGraph();
        drawGraph();

        CheckBox tollBox = new CheckBox("Avoid Tolls");
        tollBox.setOnAction(e -> avoidTolls = tollBox.isSelected());

        CheckBox emergencyBox = new CheckBox("Emergency Mode");
        emergencyBox.setOnAction(e -> emergencyMode = emergencyBox.isSelected());

        CheckBox closeAB = new CheckBox("Close A-B");
        closeAB.setOnAction(e -> edges.get("AB").setClosed(closeAB.isSelected()));

        Slider timeSlider = new Slider(0, 23, 8);
        timeSlider.setShowTickLabels(true);
        timeSlider.setShowTickMarks(true);

        Button runBtn = new Button("Recalculate");
        runBtn.setOnAction(e -> {
            int hour = (int) timeSlider.getValue();
            tm.setTime(hour, city);
            updateEdgeColors();
            runDijkstra();
        });

        VBox controls = new VBox(10,
                new Label("Controls"),
                tollBox,
                emergencyBox,
                closeAB,
                new Label("Time of Day"),
                timeSlider,
                runBtn,
                output
        );
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(graphPane);
        root.setRight(controls);

        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Traffic Simulation UI");
        stage.setScene(scene);
        stage.show();

        tm.setTime(8, city);
        updateEdgeColors();
        runDijkstra();
    }

    private void setupGraph() {
        city = new Graph();

        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");

        nodes.put("A", A);
        nodes.put("B", B);
        nodes.put("C", C);
        nodes.put("D", D);

        city.addNode(A);
        city.addNode(B);
        city.addNode(C);
        city.addNode(D);

        Edge AB = new Edge(A, B, 5, 1.2, 0, true);
        Edge AC = new Edge(A, C, 10, 1.5, 0, true);
        Edge BD = new Edge(B, D, 3, 1.1, 0, true);
        Edge CD = new Edge(C, D, 2, 2.0, 0, true);

        edges.put("AB", AB);
        edges.put("AC", AC);
        edges.put("BD", BD);
        edges.put("CD", CD);

        city.addEdge(AB);
        city.addEdge(AC);
        city.addEdge(BD);
        city.addEdge(CD);
    }

    private void drawGraph() {
        graphPane.getChildren().clear();

        Map<String, double[]> pos = Map.of(
                "A", new double[]{100, 200},
                "B", new double[]{300, 100},
                "C", new double[]{300, 300},
                "D", new double[]{500, 200}
        );

        for (String key : edges.keySet()) {
            Edge e = edges.get(key);
            double[] p1 = pos.get(e.getSource().getId());
            double[] p2 = pos.get(e.getDestination().getId());

            Line line = new Line(p1[0], p1[1], p2[0], p2[1]);
            line.setStrokeWidth(3);
            line.setUserData(e);
            graphPane.getChildren().add(line);
        }

        for (String id : pos.keySet()) {
            double[] p = pos.get(id);

            Circle c = new Circle(p[0], p[1], 15, Color.LIGHTBLUE);
            Text t = new Text(p[0] - 5, p[1] + 5, id);

            graphPane.getChildren().addAll(c, t);
        }
    }

    private void updateEdgeColors() {
        for (var node : graphPane.getChildren()) {
            if (node instanceof Line) {
                Line line = (Line) node;
                Edge e = (Edge) line.getUserData();

                if (e.isClosed()) {
                    line.setStroke(Color.GRAY);
                } else if (e.getTravelTime() > 20) {
                    line.setStroke(Color.RED);
                } else if (e.getTravelTime() > 10) {
                    line.setStroke(Color.ORANGE);
                } else {
                    line.setStroke(Color.GREEN);
                }
            }
        }
    }

    private void runDijkstra() {
        Node start = nodes.get("A");

        var result = Dijkstra.shortestPath(city, start, avoidTolls, emergencyMode);

        StringBuilder sb = new StringBuilder();
        sb.append("From A:\n");
        for (var entry : result.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }

        output.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch();
    }
}