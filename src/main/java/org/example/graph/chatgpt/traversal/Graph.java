package org.example.graph.chatgpt.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {

  static void dfs(List<List<Integer>> graph, int start) {
    int vertices = graph.size();
    boolean[] visited = new boolean[vertices];
    Stack<Integer> stack = new Stack<>();
    stack.push(start);
    while (!stack.isEmpty()) {
      int v = stack.pop();
      if (!visited[v]) {
        System.out.print(v + " ");
        visited[v] = true;
        for (int adj : graph.get(v)) {
          if (!visited[adj]) {
            stack.push(adj);
          }
        }
      }
    }
    System.out.println();
  }

  static void bfs(List<List<Integer>> graph, int start) {
    int vertices = graph.size();
    boolean[] visited = new boolean[vertices];
    visited[start] = true;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);
    while (!queue.isEmpty()) {
      int v = queue.poll();
      System.out.print(v + " ");
      for (int adj : graph.get(v)) {
        if (!visited[adj]) {
          queue.add(adj);
          visited[adj] = true;
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int vertices = 7;
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      graph.add(new ArrayList<>());
    }
    graph.get(0).add(1);
    graph.get(0).add(2);
    graph.get(1).add(3);
    graph.get(1).add(4);
    graph.get(2).add(5);
    graph.get(2).add(6);
    int start = 0;
    System.out.println("DFS traversal starting from " + start + ": ");
    dfs(graph, start);
    System.out.println("BFS traversal starting from " + start + ": ");
    bfs(graph, start);
  }
}
