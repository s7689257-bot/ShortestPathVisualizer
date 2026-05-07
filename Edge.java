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

        // Exception Handling
        // Checking null or empty vertex name
        try {

            if(name == null || name.trim().isEmpty()) {

                throw new IllegalArgumentException(
                    "Vertex name cannot be empty!"
                );
            }

            // Assign parameter value to object variable
            this.name = name;

        }
        catch(IllegalArgumentException ex) {

            System.out.println(
                "Exception: " + ex.getMessage()
            );

            // Default value
            this.name = "Unknown";
        }
    }

    // ==================================================
    // Getter method to access vertex name
    // Encapsulation is followed because variable
    // is private and accessed using method
    // ==================================================
    public String getName() {

        // Exception handling
        try {

            if(name == null) {

                throw new NullPointerException(
                    "Vertex name is null!"
                );
            }

            // Return vertex name
            return name;

        }
        catch(NullPointerException ex) {

            System.out.println(
                "Exception: " + ex.getMessage()
            );

            return "Invalid";
        }
    }

    // ==================================================
    // Setter method
    // ==================================================
    public void setName(String name) {

        try {

            if(name.length() < 1) {

                throw new IllegalArgumentException(
                    "Vertex name too short!"
                );
            }

            this.name = name;

        }
        catch(IllegalArgumentException ex) {

            System.out.println(
                "Exception: " + ex.getMessage()
            );
        }
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
    @Override
    public String toString() {

        try {

            if(name == null) {

                throw new NullPointerException(
                    "Cannot convert null name!"
                );
            }

            // Return vertex name as string
            return name;

        }
        catch(NullPointerException ex) {

            System.out.println(
                "Exception: " + ex.getMessage()
            );

            return "Invalid Vertex";
        }
    }

    // ==================================================
    // Demo method for ArithmeticException
    // ==================================================
   public void demoException() {

    try {

        System.out.println(10 / 0);

    }
    catch(ArithmeticException ex) {

        System.out.println(
            "Arithmetic Exception: Cannot divide by zero!"
        );
    }
}
}
