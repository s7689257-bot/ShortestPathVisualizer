import java.io.Serializable;

// ======================================================
// Vertex class represents a single node in the graph
// ======================================================

// Examples of vertices:
// A, B, C, D
// City names
// Network routers
// Locations in a map

// This class is very important because every graph
// is made up of vertices and edges

// ------------------------------------------------------
// Serializable interface is implemented so objects
// of Vertex class can be saved into files
// using ObjectOutputStream
// ------------------------------------------------------
public class Vertex implements Serializable {

    // --------------------------------------------------
    // Variable used to store vertex name
    // Example:
    // "A", "B", "Mumbai", etc.
    // --------------------------------------------------
    private String name;

    // ==================================================
    // Constructor of Vertex class
    // Constructor initializes vertex name
    // ==================================================
    public Vertex(String name) {

        // Assign parameter value to object variable
        this.name = name;
    }

    // ==================================================
    // Getter method to access vertex name
    // Encapsulation is followed because variable
    // is private and accessed using method
    // ==================================================
    public String getName() {

        // Return vertex name
        return name;
    }

    // ==================================================
    // toString() method
    // This method automatically runs when object
    // is printed
    // ==================================================

    // Example:
    // System.out.println(vertex);
    //
    // Without toString():
    // Vertex@4f3f5b24
    //
    // With toString():
    // A
    // ==================================================
    public String toString() {

        // Return vertex name as string
        return name;
    }
}
