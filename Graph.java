import java.util.*;

// ======================================================
// Graph class is used to represent the complete graph
// structure of the program
// ======================================================

// Graph contains:
// 1. Vertices (nodes)
// 2. Edges (connections between nodes)

// This class uses an Adjacency List representation
// because it is memory efficient and fast for traversal

public class Graph {

    // --------------------------------------------------
    // HashMap used for adjacency list representation
    //
    // Key   -> Vertex
    // Value -> List of connected edges
    //
    // Example:
    // A -> [(A,B,4), (A,C,2)]
    // --------------------------------------------------
    private Map<Vertex, List<Edge>> adjList =
            new HashMap<>();

    // ==================================================
    // Method to add a new vertex into graph
    // ==================================================
    public void addVertex(Vertex v) {

        // putIfAbsent prevents duplicate vertices
        // If vertex already exists, it will not add again
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    // ==================================================
    // Method to connect two vertices using an edge
    // ==================================================
    public void addEdge(Vertex src,
                        Vertex dest,
                        int weight) {

        // ----------------------------------------------
        // Add edge from source to destination
        // Example:
        // A -> B
        // ----------------------------------------------
        adjList.get(src)
                .add(new Edge(src, dest, weight));

        // ----------------------------------------------
        // Add reverse edge
        // This makes graph UNDIRECTED
        //
        // Meaning:
        // If A connects to B
        // then B also connects to A
        // ----------------------------------------------
        adjList.get(dest)
                .add(new Edge(dest, src, weight));
    }

    // ==================================================
    // Getter method to return adjacency list
    // ==================================================
    public Map<Vertex, List<Edge>> getAdjList() {

        // Return complete graph structure
        return adjList;
    }
}
