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

            // Assign path received from algorithm
            this.path = path;

            // Assign shortest distance
            this.distance = distance;
        }
    }

    // =========================================================
    // Main Dijkstra Algorithm Method
    // =========================================================
    public static Result getShortestPath(Graph graph, Vertex start, Vertex end) {

        // -----------------------------------------------------
        // Map to store shortest distance of each vertex
        // Key   -> Vertex
        // Value -> Distance from source vertex
        // -----------------------------------------------------
        Map<Vertex, Integer> dist = new HashMap<>();

        // -----------------------------------------------------
        // Map to store previous vertex in shortest path
        // Used later for path reconstruction
        // -----------------------------------------------------
        Map<Vertex, Vertex> prev = new HashMap<>();

        // -----------------------------------------------------
        // Initialize all vertex distances with infinity
        // Integer.MAX_VALUE acts as infinity in Java
        // -----------------------------------------------------
        for (Vertex v : graph.getAdjList().keySet()) {

            // Initially every node is unreachable
            dist.put(v, Integer.MAX_VALUE);
        }

        // -----------------------------------------------------
        // Distance of starting vertex is always 0
        // -----------------------------------------------------
        dist.put(start, 0);

        // -----------------------------------------------------
        // Priority Queue stores vertices based on minimum distance
        // Vertex with smallest distance gets highest priority
        // -----------------------------------------------------
        PriorityQueue<Vertex> pq =
                new PriorityQueue<>(Comparator.comparingInt(dist::get));

        // -----------------------------------------------------
        // Add starting vertex into priority queue
        // -----------------------------------------------------
        pq.add(start);

        // -----------------------------------------------------
        // Loop continues until queue becomes empty
        // -----------------------------------------------------
        while (!pq.isEmpty()) {

            // -------------------------------------------------
            // Remove vertex having smallest distance
            // -------------------------------------------------
            Vertex current = pq.poll();

            // -------------------------------------------------
            // Traverse all neighboring edges of current vertex
            // -------------------------------------------------
            for (Edge edge : graph.getAdjList().get(current)) {

                // ---------------------------------------------
                // Get neighboring vertex
                // ---------------------------------------------
                Vertex neighbor = edge.getDestination();

                // ---------------------------------------------
                // Calculate new possible distance
                // Formula:
                // current distance + edge weight
                // ---------------------------------------------
                int newDist =
                        dist.get(current) + edge.getWeight();

                // ---------------------------------------------
                // Check if new distance is shorter
                // than previously stored distance
                // ---------------------------------------------
                if (newDist < dist.get(neighbor)) {

                    // -----------------------------------------
                    // Update shortest distance
                    // -----------------------------------------
                    dist.put(neighbor, newDist);

                    // -----------------------------------------
                    // Store previous node for path tracking
                    // -----------------------------------------
                    prev.put(neighbor, current);

                    // -----------------------------------------
                    // Add updated vertex into queue
                    // -----------------------------------------
                    pq.add(neighbor);
                }
            }
        }

        // =====================================================
        // Path Reconstruction Section
        // =====================================================

        // Create list to store shortest path
        List<Vertex> path = new ArrayList<>();

        // -----------------------------------------------------
        // Start from destination vertex
        // Move backwards using prev map
        // -----------------------------------------------------
        for (Vertex at = end; at != null; at = prev.get(at)) {

            // Add vertex into path list
            path.add(at);
        }

        // -----------------------------------------------------
        // Reverse list because currently path is backwards
        // Example:
        // D -> C -> B -> A
        // becomes:
        // A -> B -> C -> D
        // -----------------------------------------------------
        Collections.reverse(path);

        // =====================================================
        // Return final shortest path and total distance
        // =====================================================
        return new Result(path, dist.get(end));
    }
}
