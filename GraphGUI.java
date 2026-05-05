import java.awt.*;
import javax.swing.*;

public class GraphGUI extends JFrame {

    private GraphPanel panel;
    private JLabel resultLabel;

    public GraphGUI() {
        setTitle("Dijkstra Visualizer");
        setSize(800, 600);
        setLayout(new BorderLayout());

        resultLabel = new JLabel("Path: ");
        add(resultLabel, BorderLayout.NORTH);

        panel = new GraphPanel();
        add(panel, BorderLayout.CENTER);

        JButton runBtn = new JButton("Run");
        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");

        JPanel bottom = new JPanel();
        bottom.add(runBtn);
        bottom.add(saveBtn);
        bottom.add(loadBtn);

        add(bottom, BorderLayout.SOUTH);

        runBtn.addActionListener(e -> runDijkstra());
        saveBtn.addActionListener(e -> panel.saveGraph("graph.dat"));
        loadBtn.addActionListener(e -> panel.loadGraph("graph.dat"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void runDijkstra() {

        Graph graph = new Graph();

        for (Vertex v : panel.getVertices()) {
            graph.addVertex(v);
        }

        for (Edge e : panel.getEdges()) {
            graph.addEdge(e.getSource(), e.getDestination(), e.getWeight());
        }

        String startName = JOptionPane.showInputDialog("Start vertex:");
        String endName = JOptionPane.showInputDialog("End vertex:");

        Vertex start = null, end = null;

        for (Vertex v : panel.getVertices()) {
            if (v.getName().equals(startName)) start = v;
            if (v.getName().equals(endName)) end = v;
        }

        if (start == null || end == null) {
            JOptionPane.showMessageDialog(this, "Invalid vertices!");
            return;
        }

        Dijkstra.Result result = Dijkstra.getShortestPath(graph, start, end);

        if (result.distance == Integer.MAX_VALUE) {
            resultLabel.setText("No path found!");
            return;
        }

        panel.setShortestPath(result.path);

        StringBuilder pathStr = new StringBuilder();
        for (Vertex v : result.path) {
            pathStr.append(v.getName()).append(" → ");
        }
        pathStr.setLength(pathStr.length() - 3);

        resultLabel.setText("Path: " + pathStr + " | Distance: " + result.distance);
    }
}
