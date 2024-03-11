package org.example.graph.chatgpt.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {

  static void dijkstra(List<List<Edge>> graph, int src, int[] distances) {
    PriorityQueue<Vertex> pq = new PriorityQueue<>();
    pq.add(new Vertex(src, 0));
    while (!pq.isEmpty()) {
      Vertex v = pq.poll();
      for (Edge e : graph.get(v.id)) {
        int distance = v.distance + e.weight;
        if (distance < distances[e.dest]) {
          distances[e.dest] = distance;
          pq.add(new Vertex(e.dest, distance));
        }
      }
    }
  }

  static class Vertex implements Comparable<Vertex> {
    int id;
    int distance;
    public Vertex(int id, int distance) {
      this.id = id;
      this.distance = distance;
    }
    @Override
    public int compareTo(Vertex o) {
      return Integer.compare(this.distance, o.distance);
    }
  }

  static class Edge {
    int dest;
    int weight;
    public Edge(int dest, int weight) {
      this.dest = dest;
      this.weight = weight;
    }
  }

  public static void main(String[] args) {
    int src = 0;
    int vertices = 5;
    List<List<Edge>> graph = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      graph.add(new ArrayList<>());
    }
    int[] distances = new int[vertices];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[src] = 0;
    graph.get(0).add(new Edge(1, 2));
    graph.get(0).add(new Edge(2, 4));
    graph.get(1).add(new Edge(3, 3));
    graph.get(2).add(new Edge(3, 1));
    graph.get(3).add(new Edge(4, 5));
    dijkstra(graph, src, distances);
    System.out.println("Shortest distance from the source ("  +src + "): ");
    for (int i = 0; i < vertices; i++) {
      System.out.println("To " + i + ": " + distances[i]);
    }
  }
}
