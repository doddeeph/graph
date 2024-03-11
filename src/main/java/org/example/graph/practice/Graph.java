package org.example.graph.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph {

  static long countPaths(List<List<Integer>> graph, int src, int dest, boolean[] visited) {
    if (src == dest) {
      return 1;
    }
    if (visited[src]) {
      return -1;
    }
    visited[src] = true;
    long count = 0;
    for (int adj : graph.get(src)) {
      long adjCount = countPaths(graph, adj, dest, visited) % 1000000000;
      if (adjCount == -1) {
        return -1;
      }
      count += adjCount % 1000000000;
    }
    visited[src] = false;
    return count;
  }

  static void findAllPaths(List<List<Integer>> graph, int src, int dest,
      List<List<Integer>> allPaths, List<Integer> currPath) {
    currPath.add(src);
    if (src == dest) {
      allPaths.add(new ArrayList<>(currPath));
    } else {
      for (int adj : graph.get(src)) {
        if (!currPath.contains(adj)) {
          findAllPaths(graph, adj, dest, allPaths, currPath);
        }
      }
    }
    //backtrack
    currPath.remove(currPath.size() - 1);
  }

  static List<List<Integer>> getAllPaths(List<List<Integer>> graph, int src, int dest) {
    List<List<Integer>> allPaths = new ArrayList<>();
    List<Integer> currPath = new ArrayList<>();
    findAllPaths(graph, src, dest, allPaths, currPath);
    return allPaths;
  }

  // Dijkstra
  static int[] calcShortestPath(Map<Integer, List<Node>> graph, int root) {
    int V = graph.keySet().size();
    boolean[] visited = new boolean[V];
    int[] distances = new int[V];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[root] = 0;
    PriorityQueue<Node> minHeap = new PriorityQueue<>(V, new Node());
    minHeap.add(new Node(root, 0));
    while (!minHeap.isEmpty()) {
      Node src = minHeap.poll();
      if (!visited[src.v] && graph.containsKey(src.v)) {
        visited[src.v] = true;
        for (Node dest : graph.get(src.v)) {
          if (!visited[dest.v] && (distances[src.v] + dest.distance < distances[dest.v])) {
            distances[dest.v] = distances[src.v] + dest.distance;
            minHeap.add(new Node(dest.v, distances[dest.v]));
          }
        }
      }
    }
    return distances;
  }

  static void addEdge(Map<Integer, List<Node>> graph, int src, int dest, int distance,
      boolean isDirectedGraph) {
    graph.putIfAbsent(src, new LinkedList<>());
    graph.get(src).add(new Node(dest, distance));
    if (!isDirectedGraph) {
      graph.putIfAbsent(dest, new LinkedList<>());
      graph.get(dest).add(new Node(src, distance));
    }
  }

  static class Node implements Comparator<Node> {
    int v;
    int distance;
    public Node() {
    }
    public Node(int v, int distance) {
      this.v = v;
      this.distance = distance;
    }
    @Override
    public int compare(Node o1, Node o2) {
      return Integer.compare(o1.distance, o2.distance);
    }
  }

  // Stack - Last In First Out
  static void dfs(Map<Integer, List<Node>> graph, int root) {
    boolean[] visited = new boolean[graph.keySet().size()];
    Stack<Integer> stack = new Stack<>();
    stack.add(root);
    visited[root] = true;
    while (!stack.isEmpty()) {
      int v = stack.pop();
      System.out.print(v + " ");
      for (Node adj : graph.get(v)) {
        if (!visited[adj.v]) {
          stack.push(adj.v);
          visited[adj.v] = true;
        }
      }
    }
    System.out.println();
  }

  // Queue - First In First Out
  static void bfs(Map<Integer, List<Node>> graph, int root) {
    boolean[] visited = new boolean[graph.keySet().size()];
    Queue<Integer> q = new LinkedList<>();
    q.add(root);
    visited[root] = true;
    while (!q.isEmpty()) {
      int v = q.poll();
      System.out.print(v + " ");
      for (Node adj : graph.get(v)) {
        if (!visited[adj.v]) {
          q.add(adj.v);
          visited[adj.v] = true;
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    System.out.println("------- Traversal -------");
    Map<Integer, List<Node>> graph = new HashMap<>();
    addEdge(graph, 0, 1, 0, false);
    addEdge(graph, 0, 2, 0, false);
    addEdge(graph, 1, 3, 0, false);
    addEdge(graph, 1, 4, 0, false);
    addEdge(graph, 2, 5, 0, false);
    addEdge(graph, 2, 6, 0, false);
    System.out.print("DFS: ");
    dfs(graph, 0);
    System.out.print("BFS: ");
    bfs(graph, 0);

    System.out.println("------- Dijkstra -------");
    graph = new HashMap<>();
    addEdge(graph, 0, 1, 4, false);
    addEdge(graph, 0, 7, 8, false);
    addEdge(graph, 1, 2, 8, false);
    addEdge(graph, 1, 7, 11, false);
    addEdge(graph, 2, 3, 7, false);
    addEdge(graph, 2, 8, 2, false);
    addEdge(graph, 2, 5, 4, false);
    addEdge(graph, 3, 4, 9, false);
    addEdge(graph, 3, 5, 14, false);
    addEdge(graph, 4, 5, 10, false);
    addEdge(graph, 5, 6, 2, false);
    addEdge(graph, 6, 7, 1, false);
    addEdge(graph, 6, 8, 6, false);
    addEdge(graph, 7, 8, 7, false);
    int root = 0;
    int[] distances = calcShortestPath(graph, root);
    System.out.println("The shorted path from node :");
    for (int i = 0; i < distances.length; i++) {
      System.out.println(root + " to " + i + " is " + distances[i]);
    }

    System.out.println("------- Network Delay Time -------");
    graph = new HashMap<>();
    int V = 5;
    for (int i = 0; i < V; i++) {
      graph.put(i, new LinkedList<>());
    }
    addEdge(graph, 2, 1, 1, true);
    addEdge(graph, 2, 3, 1, true);
    addEdge(graph, 3, 4, 1, true);
    distances = calcShortestPath(graph, 2);
    System.out.println("#1 Output: " + distances[V-1]);
    graph = new HashMap<>();
    V = 3;
    for (int i = 0; i < V; i++) {
      graph.put(i, new LinkedList<>());
    }
    addEdge(graph, 1, 2, 1, true);
    distances = calcShortestPath(graph, 1);
    System.out.println("#2 Output: " + distances[V-1]);
    graph = new HashMap<>();
    V = 3;
    for (int i = 0; i < V; i++) {
      graph.put(i, new LinkedList<>());
    }
    addEdge(graph, 1, 2, 1, true);
    distances = calcShortestPath(graph, 2);
    System.out.println("#3 Output: " + distances[V-1]);

    System.out.println("------- Get All Paths -------");
    //https://www.hackerrank.com/contests/srin-practice/challenges/kingdom-connectivity
    V = 6;
    List<List<Integer>> g = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      g.add(new ArrayList<>());
    }
    g.get(1).add(2);
    g.get(2).add(3);
    g.get(4).add(2);
    //g.get(2).add(4);
    g.get(3).add(4);
    g.get(4).add(5);
    int src = 1;
    int dest = 5;
    List<List<Integer>> allPaths = getAllPaths(g, src, dest);
    for (List<Integer> path : allPaths) {
      System.out.println(path);
    }

    System.out.println("------- Count All Paths -------");
    long count = countPaths(g, src, dest, new boolean[V]);
    System.out.println(count != -1 ? count : "INFINITE PATHS (cyclic)");
  }
}
