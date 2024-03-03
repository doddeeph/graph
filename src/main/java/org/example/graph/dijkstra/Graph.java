package org.example.graph.dijkstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Graph {

  private boolean directedGraph;
  private Map<Integer, List<Node>> adjList;
  private Set<Integer> visited;
  private int[] distances;
  private PriorityQueue<Node> minHeap;
  private Map<Integer, String> mapping;

  public Graph(int V, boolean directedGraph) {
    this.directedGraph = directedGraph;
    adjList = new HashMap<>();
    visited = new HashSet<>();
    distances = new int[V];
    Arrays.fill(distances, Integer.MAX_VALUE);
    minHeap = new PriorityQueue<>(V, new Node());
    mapping = new HashMap<>();
  }

  public void addEdge(int src, int dest, int distance) {
    adjList.putIfAbsent(src, new LinkedList<>());
    adjList.get(src).add(new Node(dest, distance));
    if (!directedGraph) {
      adjList.putIfAbsent(dest, new LinkedList<>());
      adjList.get(dest).add(new Node(src, distance));
    }
  }

  public void calcShortestPath(int root) {
    distances[root] = 0;
    minHeap.add(new Node(root, 0));
    while (!minHeap.isEmpty()) {
      Node node = minHeap.poll();
      int src = node.v;
      if (visited.contains(src)) continue;
      visited.add(src);
      if (!adjList.containsKey(src)) continue;
      calcMinDistances(src);
    }
    print(root);
  }

  public void calcMinDistances(int src) {
    for (Node node : adjList.get(src)) {
      int dest = node.v;
      int destDistance = node.distance;
      if (!visited.contains(dest) && (distances[src] + destDistance < distances[dest])) {
        distances[dest] = distances[src] + destDistance;
        minHeap.add(new Node(dest, distances[dest]));
      }
    }
  }

  public void setMapping(Map<Integer, String> mapping) {
    this.mapping = mapping;
  }

  public void print(int root) {
    System.out.println("The shorted path from node :");
    for (int i = 0; i < distances.length; i++) {
      System.out.println(mapping.getOrDefault(root, String.valueOf(root)) +
          " to " + mapping.getOrDefault(i, String.valueOf(i)) +
          " is " + distances[i]);
    }
  }

  static class Node implements Comparator<Node> {

    private int v;
    private int distance;

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

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testCase = sc.nextInt();
    sc.nextLine();
    Graph g;

    for (int i = 0; i < testCase; i++) {
      String[] s = sc.nextLine().split(" ");
      int directedGraph = Integer.parseInt(s[0]);
      int V = Integer.parseInt(s[1]);
      int E = Integer.parseInt(s[2]);
      int K = Integer.parseInt(s[3]);
      g = new Graph(V, directedGraph == 1);

      for (int j = 0; j < E; j++) {
        s = sc.nextLine().split(" ");
        int src = Integer.parseInt(s[0]);
        int dest = Integer.parseInt(s[1]);
        int distance = Integer.parseInt(s[2]);
        g.addEdge(src, dest, distance);
      }

      g.calcShortestPath(K);
    }
  }
}
