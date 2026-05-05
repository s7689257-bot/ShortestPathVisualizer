import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GraphPanel extends JPanel {

    private Map<Vertex, Point> positions = new HashMap<>();
    private java.util.List<Edge> edges = new ArrayList<>();

    private Vertex selectedVertex = null;
    private int radius = 20;

    private java.util.List<Vertex> shortestPath = new ArrayList<>();

    public GraphPanel() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                Vertex clicked = getVertexAt(e.getPoint());

                if (clicked == null) {
                    String name = JOptionPane.showInputDialog("Enter vertex name:");
                    if (name != null && !name.isEmpty()) {
                        Vertex v = new Vertex(name);
                        positions.put(v, e.getPoint());
                        repaint();
                    }
                } else {
                    if (selectedVertex == null) {
                        selectedVertex = clicked;
                    } else {
                        String w = JOptionPane.showInputDialog("Enter weight:");
                        try {
                            int weight = Integer.parseInt(w);
                            edges.add(new Edge(selectedVertex, clicked, weight));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Invalid weight!");
                        }
                        selectedVertex = null;
                        repaint();
                    }
                }
            }
        });
    }

    private Vertex getVertexAt(Point p) {
        for (Vertex v : positions.keySet()) {
            Point pt = positions.get(v);
            if (p.distance(pt) <= radius) {
                return v;
            }
        }
        return null;
    }

    public Set<Vertex> getVertices() {
        return positions.keySet();
    }

    public java.util.List<Edge> getEdges() {
        return edges;
    }

    public void setShortestPath(java.util.List<Vertex> path) {
        this.shortestPath = path;
        repaint();
    }

    // 🔥 SAVE
    public void saveGraph(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(positions);
            out.writeObject(edges);
            JOptionPane.showMessageDialog(this, "Graph saved!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving file!");
        }
    }

    // 🔥 LOAD
    @SuppressWarnings("unchecked")
    public void loadGraph(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            positions = (Map<Vertex, Point>) in.readObject();
            edges = (java.util.List<Edge>) in.readObject();
            shortestPath.clear();
            repaint();
            JOptionPane.showMessageDialog(this, "Graph loaded!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading file!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // edges
        for (Edge e : edges) {
            Point p1 = positions.get(e.getSource());
            Point p2 = positions.get(e.getDestination());

            g.setColor(Color.BLACK);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);

            int midX = (p1.x + p2.x) / 2;
            int midY = (p1.y + p2.y) / 2;
            g.drawString(String.valueOf(e.getWeight()), midX, midY);
        }

        // shortest path
        g.setColor(Color.RED);
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            Point p1 = positions.get(shortestPath.get(i));
            Point p2 = positions.get(shortestPath.get(i + 1));
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // vertices
        for (Vertex v : positions.keySet()) {
            Point p = positions.get(v);

            g.setColor(Color.BLUE);
            g.fillOval(p.x - radius, p.y - radius, 2 * radius, 2 * radius);

            g.setColor(Color.WHITE);
            g.drawString(v.getName(), p.x - 5, p.y + 5);
        }
    }
}
