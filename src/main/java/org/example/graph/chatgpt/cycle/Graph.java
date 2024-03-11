package org.example.graph.chatgpt.cycle;

import java.util.ArrayList;
import java.util.List;

public class Graph {

  static boolean hasCycle(List<List<Integer>> graph, int v, boolean[] visited, boolean[] stack) {
    visited[v] = true;
    stack[v] = true;
    for (int adj : graph.get(v)) {
      if ((!visited[adj] && hasCycle(graph, adj, visited, stack)) || stack[adj]) {
        return true;
      }
    }
    stack[v] = false;
    return false;
  }

  static boolean isCyclic(List<List<Integer>> graph, int vertices) {
    boolean[] visited = new boolean[vertices];
    boolean[] stack = new boolean[vertices];
    for (int v = 0; v < vertices; v++) {
      if (!visited[v] && hasCycle(graph, v, visited, stack)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int vertices = 5;
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      graph.add(new ArrayList<>());
    }
    graph.get(0).add(1);
    graph.get(1).add(2);
    graph.get(2).add(0);
    graph.get(1).add(3);
    graph.get(3).add(4);
    System.out.println("The graph contains a cycle? " + isCyclic(graph, vertices));

    vertices = 7;
    graph = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      graph.add(new ArrayList<>());
    }
    graph.get(0).add(1);
    graph.get(0).add(2);
    graph.get(1).add(3);
    graph.get(1).add(4);
    graph.get(2).add(5);
    graph.get(2).add(6);
    System.out.println("The graph contains a cycle? " + isCyclic(graph, vertices));
  }
}
