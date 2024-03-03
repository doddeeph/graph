package org.example.graph.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Graph<T> {

  private Map<T, List<T>> adjList;
  private boolean directedGraph;

  public Graph(boolean directedGraph) {
    adjList = new HashMap<>();
    this.directedGraph = directedGraph;
  }

  public void addVertex(T v) {
    adjList.putIfAbsent(v, new LinkedList<>());
  }

  public void removeVertex(T v) {
    if (adjList.containsKey(v)) {
      adjList.remove(v);
      adjList.values().forEach(value -> value.remove(v));
    }
  }

  public void addEdge(T v1, T v2) {
    if (adjList.containsKey(v1) && adjList.containsKey(v2)) {
      adjList.get(v1).add(v2);
      if (!directedGraph) {
        adjList.get(v2).add(v1);
      }
    }
  }

  public void removeEdge(T v1, T v2) {
    if (adjList.containsKey(v1) && adjList.containsKey(v2)) {
      adjList.get(v1).remove(v2);
      if (!directedGraph) {
        adjList.get(v2).remove(v1);
      }
    }
  }

  public List<T> getAdjNodes(T v) {
    if (adjList.containsKey(v)) {
      return adjList.get(v);
    }
    return new ArrayList<>();
  }

  public boolean isConnected(T v1, T v2) {
    if (adjList.containsKey(v1)) {
      return adjList.get(v1).contains(v2);
    }
    return false;
  }

  public int getVerticesCount() {
    return adjList.keySet().size();
  }

  public Set<T> dfs(T root) {
    Set<T> visited = new LinkedHashSet<>();
    Stack<T> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      T v = stack.pop();
      if (!visited.contains(v)) {
        visited.add(v);
        for (T t : getAdjNodes(v)) {
          stack.push(t);
        }
      }
    }
    return visited;
  }

  public Set<T> bfs(T root) {
    Set<T> visited = new LinkedHashSet<>();
    Queue<T> queue = new LinkedList<>();
    queue.add(root);
    visited.add(root);
    while (!queue.isEmpty()) {
      T v = queue.poll();
      for (T t : getAdjNodes(v)) {
        if (!visited.contains(t)) {
          queue.add(t);
          visited.add(t);
        }
      }
    }
    return visited;
  }

  public void printTraversal(T root) {
    String sb = "Depth-First Traversal: "
        + dfs(root).stream().map(String::valueOf).collect(Collectors.joining(" -> "))
        + System.lineSeparator()
        + "Breadth-First Traversal: "
        + bfs(root).stream().map(String::valueOf).collect(Collectors.joining(" -> "));
    System.out.println(sb);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    adjList.forEach((key, value) -> {
      sb.append(key).append(": ");
      sb.append(value.stream().map(String::valueOf).collect(Collectors.joining(" -> ")));
      sb.append(System.lineSeparator());
    });
    return sb.toString();
  }

  public static void main(String[] args) {
    Graph<String> g1 = new Graph<>(false);
    List<String> vertices1 = Arrays.asList("Bob", "Alice", "Mark", "Rob", "Maria");
    vertices1.forEach(g1::addVertex);
    g1.addEdge("Bob", "Alice");
    g1.addEdge("Bob", "Rob");
    g1.addEdge("Alice", "Mark");
    g1.addEdge("Rob", "Mark");
    g1.addEdge("Alice", "Maria");
    g1.addEdge("Rob", "Maria");
    System.out.println(g1);
    g1.printTraversal("Bob");

    Graph<Integer> g2 = new Graph<>(false);
    List<Integer> vertices2 = Arrays.asList(1, 2, 3, 4, 5);
    vertices2.forEach(g2::addVertex);
    g2.addEdge(1, 2);
    g2.addEdge(1, 4);
    g2.addEdge(2, 3);
    g2.addEdge(4, 3);
    g2.addEdge(2, 5);
    g2.addEdge(4, 5);
    System.out.println(g2);
    g2.printTraversal(1);
  }
}
