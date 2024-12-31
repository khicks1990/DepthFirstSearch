/*Here's a visualization of the adjacency list representing the graph:

Copy
A: B, C
B: A, D, E
C: A, F
D: B
E: B, F
F: C, E
In this visualization, each vertex is shown as a key followed by a colon, and its neighbors are listed after the colon. For example, vertex "A" is connected to vertices "B" and "C", vertex "B" is connected to vertices "A", "D", and "E", and so on.*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create an adjacency list representing the graph
        Map<String, List<String>> adjList = new HashMap<>();
        adjList.put("A", Arrays.asList("B", "C"));
        adjList.put("B", Arrays.asList("A", "D", "E"));
        adjList.put("C", Arrays.asList("A", "F"));
        adjList.put("D", Arrays.asList("B"));
        adjList.put("E", Arrays.asList("B", "F"));
        adjList.put("F", Arrays.asList("C", "E"));

        String startVertex = "A";
        String targetVertex = "F";

        // Perform depth-first search
        List<String> path = dfs(startVertex, targetVertex, adjList);

        // Print the path or a message if no path is found
        if (path != null) {
            System.out.println("A path is found from vertex " + startVertex + " to vertex " + targetVertex + ":");
            System.out.println(path);
        } else {
            System.out.println("No path is found from vertex " + startVertex + " to vertex " + targetVertex + ".");
        }
    }

    static List<String> dfs(String start, String target, Map<String, List<String>> adjList) {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();

        // Push the start vertex onto the stack
        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();

            // Check if we have reached the target vertex
            if (current.equals(target)) {
                // Reconstruct the path
                List<String> path = new ArrayList<>();
                path.add(current);
                String parent = parentMap.get(current);
                while (parent != null) {
                    path.add(0, parent);
                    parent = parentMap.get(parent);
                }
                return path;
            }

            // Push the unvisited neighbors onto the stack
            List<String> neighbors = adjList.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                        visited.add(neighbor);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }

        // No path found
        return null;
    }
}