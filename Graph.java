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
    // Constructor
    // ==================================================
    public Graph() {

        try {

            // Check if adjacency list is initialized
            if(adjList == null) {

                throw new NullPointerException(
                    "Adjacency list not initialized!"
                );
            }

        }
        catch(NullPointerException ex) {

            System.out.println(
                "Exception: " + ex.getMessage()
            );

            adjList = new HashMap<>();
        }
    }

    // ==================================================
    // Method to add a new vertex into graph
    // ==================================================
    public void addVertex(Vertex v) {

        try {

            // ------------------------------------------
            // Null checking
            // ------------------------------------------
            if(v == null) {

                throw new NullPointerException(
                    "Vertex cannot be null!"
                );
            }

            // ------------------------------------------
            // Duplicate vertex checking
            // ------------------------------------------
            if(adjList.containsKey(v)) {

                throw new IllegalArgumentException(
                    "Duplicate vertex not allowed!"
                );
            }

            // Add vertex
            adjList.putIfAbsent(v,
                    new ArrayList<>());

            System.out.println(
                "Vertex added successfully!"
            );

        }
        catch(NullPointerException ex) {

            System.out.println(
                "Null Pointer Exception: "
                + ex.getMessage()
            );
        }
        catch(IllegalArgumentException ex) {

            System.out.println(
                "Illegal Argument Exception: "
                + ex.getMessage()
            );
        }
    }

    // ==================================================
    // Method to connect two vertices using an edge
    // ==================================================
    public void addEdge(Vertex src,
                        Vertex dest,
                        int weight) {

        try {

            // ------------------------------------------
            // Null vertex checking
            // ------------------------------------------
            if(src == null || dest == null) {

                throw new NullPointerException(
                    "Source/Destination vertex is null!"
                );
            }

            // ------------------------------------------
            // Vertex existence checking
            // ------------------------------------------
            if(!adjList.containsKey(src)
                    || !adjList.containsKey(dest)) {

                throw new NoSuchElementException(
                    "Vertex does not exist in graph!"
                );
            }

            // ------------------------------------------
            // Negative weight checking
            // ------------------------------------------
            if(weight < 0) {

                throw new IllegalArgumentException(
                    "Negative weights are not allowed!"
                );
            }

            // ------------------------------------------
            // Add edge from source to destination
            // Example:
            // A -> B
            // ------------------------------------------
            adjList.get(src)
                    .add(new Edge(src,
                                  dest,
                                  weight));

            // ------------------------------------------
            // Add reverse edge
            // This makes graph UNDIRECTED
            // ------------------------------------------
            adjList.get(dest)
                    .add(new Edge(dest,
                                  src,
                                  weight));

            System.out.println(
                "Edge added successfully!"
            );

        }
        catch(NullPointerException ex) {

            System.out.println(
                "Null Pointer Exception: "
                + ex.getMessage()
            );
        }
        catch(NoSuchElementException ex) {

            System.out.println(
                "No Such Element Exception: "
                + ex.getMessage()
            );
        }
        catch(IllegalArgumentException ex) {

            System.out.println(
                "Illegal Argument Exception: "
                + ex.getMessage()
            );
        }
    }

    // ==================================================
    // Getter method to return adjacency list
    // ==================================================
    public Map<Vertex, List<Edge>> getAdjList() {

        try {

            // ------------------------------------------
            // Empty graph checking
            // ------------------------------------------
            if(adjList.isEmpty()) {

                throw new EmptyStackException();
            }

            // Return complete graph structure
            return adjList;

        }
        catch(EmptyStackException ex) {

            System.out.println(
                "Graph is empty!"
            );

            return new HashMap<>();
        }
    }

    // ==================================================
    // Method to display graph
    // ==================================================
    public void displayGraph() {

        try {

            if(adjList.isEmpty()) {

                throw new Exception(
                    "Cannot display empty graph!"
                );
            }

            for(Vertex v : adjList.keySet()) {

                System.out.println(
                    v + " -> " +
                    adjList.get(v)
                );
            }

        }
        catch(Exception ex) {

            System.out.println(
                "Exception: "
                + ex.getMessage()
            );
        }
    }

    // ==================================================
    // Demo method for ArithmeticException
    // ==================================================
    public void demoException() {

        try {

            System.out.println(100 / 0);

        }
        catch(ArithmeticException ex) {

            System.out.println(
                "Arithmetic Exception: "
                + "Cannot divide by zero!"
            );
        }
    }
}
