package service;

import java.util.*;

public class LocationService {

    private Map<String, Map<String, Integer>> graph = new HashMap<>();

    public void addEdge(String from, String to, int distance) {
        graph.putIfAbsent(from, new HashMap<>());
        graph.putIfAbsent(to, new HashMap<>());
        graph.get(from).put(to, distance);
        graph.get(to).put(from, distance);
    }

    public int getShortestDistance(String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) return Integer.MAX_VALUE;

        Map<String, Integer> dist = new HashMap<>();
        for (String node : graph.keySet()) dist.put(node, Integer.MAX_VALUE);
        dist.put(start, 0);

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(start);

        while (!pq.isEmpty()) {
            String current = pq.poll();
            int currentDist = dist.get(current);

            for (Map.Entry<String, Integer> neighbor : graph.get(current).entrySet()) {
                int newDist = currentDist + neighbor.getValue();
                if (newDist < dist.get(neighbor.getKey())) {
                    dist.put(neighbor.getKey(), newDist);
                    pq.add(neighbor.getKey());
                }
            }
        }

        return dist.get(end);
    }

    public Set<String> getLocations() {
        return graph.keySet();
    }
}
