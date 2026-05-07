import java.util.*;

public class Dijkstra {

    // =========================================================
    // Inner class used to store the final shortest path result
    // =========================================================
    public static class Result {

        // List to store the path from source to destination
        public List<Vertex> path;

        // Variable to store total shortest distance
        public int distance;

        // Constructor to initialize path and distance
        public Result(List<Vertex> path, int distance) {

            try {

                // ---------------------------------------------
                // Null path checking
                // ---------------------------------------------
                if(path == null) {

                    throw new NullPointerException(
                        "Path cannot be null!"
                    );
                }

                // ---------------------------------------------
                // Negative distance checking
                // ---------------------------------------------
                if(distance < 0) {

                    throw new IllegalArgumentException(
                        "Distance cannot be negative!"
                    );
                }

                // Assign path received from algorithm
                this.path = path;

                // Assign shortest distance
                this.distance = distance;

            }
            catch(NullPointerException ex) {

                System.out.println(
                    "Null Pointer Exception: "
                    + ex.getMessage()
                );

                this.path = new ArrayList<>();
            }
            catch(IllegalArgumentException ex) {

                System.out.println(
                    "Illegal Argument Exception: "
                    + ex.getMessage()
                );

                this.distance = 0;
            }
        }
    }

    // =========================================================
    // Main Dijkstra Algorithm Method
    // =========================================================
    public static Result getShortestPath(Graph graph,
                                         Vertex start,
                                         Vertex end) {

        try {

            // ---------------------------------------------
            // Null checking
            // ---------------------------------------------
            if(graph == null ||
               start == null ||
               end == null) {

                throw new NullPointerException(
                    "Graph/Start/End cannot be null!"
                );
            }

            // ---------------------------------------------
            // Empty graph checking
            // ---------------------------------------------
            if(graph.getAdjList().isEmpty()) {

                throw new NoSuchElementException(
                    "Graph is empty!"
                );
            }

            // ---------------------------------------------
            // Map to store shortest distance
            // ---------------------------------------------
            Map<Vertex, Integer> dist =
                    new HashMap<>();

            // ---------------------------------------------
            // Map to store previous vertex
            // ---------------------------------------------
            Map<Vertex, Vertex> prev =
                    new HashMap<>();

            // ---------------------------------------------
            // Initialize all distances with infinity
            // ---------------------------------------------
            for (Vertex v :
                    graph.getAdjList().keySet()) {

                dist.put(v, Integer.MAX_VALUE);
            }

            // ---------------------------------------------
            // Distance of source vertex = 0
            // ---------------------------------------------
            dist.put(start, 0);

            // ---------------------------------------------
            // Priority Queue
            // ---------------------------------------------
            PriorityQueue<Vertex> pq =
                    new PriorityQueue<>(
                            Comparator.comparingInt(
                                    dist::get));

            // Add source vertex
            pq.add(start);

            // =============================================
            // Main Algorithm Loop
            // =============================================
            while (!pq.isEmpty()) {

                // Remove minimum distance vertex
                Vertex current = pq.poll();

                // Traverse neighbors
                for (Edge edge :
                        graph.getAdjList()
                                .get(current)) {

                    // Get neighbor
                    Vertex neighbor =
                            edge.getDestination();

                    // -------------------------------------
                    // Integer overflow checking
                    // -------------------------------------
                    if(dist.get(current)
                            == Integer.MAX_VALUE) {

                        continue;
                    }

                    // Calculate new distance
                    int newDist =
                            dist.get(current)
                            + edge.getWeight();

                    // -------------------------------------
                    // Arithmetic checking
                    // -------------------------------------
                    if(newDist < 0) {

                        throw new ArithmeticException(
                            "Distance overflow occurred!"
                        );
                    }

                    // Update shorter distance
                    if(newDist <
                            dist.get(neighbor)) {

                        dist.put(neighbor,
                                newDist);

                        prev.put(neighbor,
                                current);

                        pq.add(neighbor);
                    }
                }
            }

            // =============================================
            // Path Reconstruction
            // =============================================
            List<Vertex> path =
                    new ArrayList<>();

            // Build reverse path
            for (Vertex at = end;
                 at != null;
                 at = prev.get(at)) {

                path.add(at);
            }

            // Reverse path
            Collections.reverse(path);

            // =============================================
            // No path checking
            // =============================================
            if(path.isEmpty()) {

                throw new Exception(
                    "No shortest path found!"
                );
            }

            // Return result
            return new Result(path,
                    dist.get(end));

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
        catch(ArithmeticException ex) {

            System.out.println(
                "Arithmetic Exception: "
                + ex.getMessage()
            );
        }
        catch(IllegalArgumentException ex) {

            System.out.println(
                "Illegal Argument Exception: "
                + ex.getMessage()
            );
        }
        catch(Exception ex) {

            System.out.println(
                "General Exception: "
                + ex.getMessage()
            );
        }

        // Return default result if exception occurs
        return new Result(
                new ArrayList<>(),
                0
        );
    }

    // =========================================================
    // Demo method for exception handling
    // =========================================================
    public static void demoException() {

        try {

            System.out.println(100 / 0);

        }
        catch(ArithmeticException ex) {

            System.out.println(
                "Arithmetic Exception: "
                + "Division by zero!"
            );
        }
    }
}
