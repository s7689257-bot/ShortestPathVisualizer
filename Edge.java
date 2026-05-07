import java.io.Serializable;

// ======================================================
// Edge class represents a connection between two vertices
// in the graph
// ======================================================

// Serializable interface is used for file handling
// so Edge objects can be saved into files
public class Edge implements Serializable {

    // --------------------------------------------------
    // Source vertex of the edge
    // Example:
    // If edge is A -> B
    // then source = A
    // --------------------------------------------------
    private Vertex source;

    // --------------------------------------------------
    // Destination vertex of the edge
    // Example:
    // If edge is A -> B
    // then destination = B
    // --------------------------------------------------
    private Vertex destination;

    // --------------------------------------------------
    // Weight represents cost/distance of edge
    // Example:
    // Road distance between two cities
    // --------------------------------------------------
    private int weight;

    // ==================================================
    // Constructor to initialize all edge values
    // ==================================================
    public Edge(Vertex source, Vertex destination, int weight) {

        // Assign source vertex
        this.source = source;

        // Assign destination vertex
        this.destination = destination;

        // Assign edge weight
        this.weight = weight;
    }

    // ==================================================
    // Getter method for source vertex
    // ==================================================
    public Vertex getSource() {

        // Return source node
        return source;
    }

    // ==================================================
    // Getter method for destination vertex
    // ==================================================
    public Vertex getDestination() {

        // Return destination node
        return destination;
    }

    // ==================================================
    // Getter method for edge weight
    // ==================================================
    public int getWeight() {

        // Return edge cost/distance
        return weight;
    }
}
