import java.util.*;

public class Graph {
    private Map<Vertex, List<Edge>> adjList = new HashMap<>();

    public void addVertex(Vertex v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(Vertex src, Vertex dest, int weight) {
        adjList.get(src).add(new Edge(src, dest, weight));
        adjList.get(dest).add(new Edge(dest, src, weight)); // 🔥 undirected
    }

    public Map<Vertex, List<Edge>> getAdjList() {
        return adjList;
    }
}
