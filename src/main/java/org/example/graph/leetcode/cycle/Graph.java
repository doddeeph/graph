package org.example.graph.leetcode.cycle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

  static boolean isCyclic(List<List<Integer>> graph, boolean isBFS) {
    int V = graph.size();
    boolean[] visited = new boolean[V];
    for (int v = 0; v < V; v++) {
      if (!visited[v]) {
        return isBFS ? hasCycleBFS(graph, v, visited) : hasCycleDFS(graph, v, -1, visited);
      }
    }
    return false;
  }

  static boolean hasCycleBFS(List<List<Integer>> graph, int v, boolean[] visited) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{v, -1});
    visited[v] = true;
    while (!q.isEmpty()) {
      int[] arr = q.poll();
      for (int adj : graph.get(v)) {
        if (!visited[adj]) {
          q.add(new int[]{adj, arr[0]});
          visited[adj] = true;
        } else if (arr[1] != adj) {
          return true;
        }
      }
    }
    return false;
  }

  static boolean hasCycleDFS(List<List<Integer>> graph, int v, int par, boolean[] visited) {
    visited[v] = true;
    for (int adj : graph.get(v)) {
      if (!visited[adj]) {
        if (hasCycleDFS(graph, adj, v, visited) || adj != par) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      graph.add(new ArrayList<>());
    }
    graph.get(0).add(1);
    graph.get(0).add(2);
    graph.get(2).add(3);
    graph.get(1).add(3);
    graph.get(2).add(4);
    System.out.println("#BFS Is Cyclic Graph? " + (isCyclic(graph, true)? "Yes" : "No"));
    System.out.println("#DFS Is Cyclic Graph? " + (isCyclic(graph, false)? "Yes" : "No"));
  }
}
