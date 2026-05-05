import java.util.*;

public class Dijkstra {

    public static class Result {
        public List<Vertex> path;
        public int distance;

        public Result(List<Vertex> path, int distance) {
            this.path = path;
            this.distance = distance;
        }
    }

    public static Result getShortestPath(Graph graph, Vertex start, Vertex end) {

        Map<Vertex, Integer> dist = new HashMap<>();
        Map<Vertex, Vertex> prev = new HashMap<>();

        for (Vertex v : graph.getAdjList().keySet()) {
            dist.put(v, Integer.MAX_VALUE);
        }

        dist.put(start, 0);

        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();

            for (Edge edge : graph.getAdjList().get(current)) {
                Vertex neighbor = edge.getDestination();
                int newDist = dist.get(current) + edge.getWeight();

                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        List<Vertex> path = new ArrayList<>();
        for (Vertex at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }

        Collections.reverse(path);

        return new Result(path, dist.get(end));
    }
}