package org.example.graph.cycle;

import java.util.ArrayList;
import java.util.List;

public class Graph {

  private List<Vertex> vertices;

  public Graph() {
    vertices = new ArrayList<>();
  }

  public void addVertex(Vertex v) {
    vertices.add(v);
  }

  public void addEdge(Vertex from, Vertex to) {
    from.adjList.add(to);
  }

  public boolean hasCycle() {
    for (Vertex v : vertices) {
      if (!v.visited && hasCycle(v)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasCycle(Vertex srcVertex) {
    srcVertex.beingVisited = true;
    for (Vertex neighbor : srcVertex.adjList) {
      if (neighbor.beingVisited) {
        return true;
      } else if (!neighbor.visited && hasCycle(neighbor)) {
        return true;
      }
    }
    srcVertex.beingVisited =  false;
    srcVertex.visited = true;
    return false;
  }

  private static class Vertex {

    private String label;
    private boolean beingVisited;
    private boolean visited;
    private List<Vertex> adjList;

    public Vertex(String label) {
      this.label = label;
      this.adjList = new ArrayList<>();
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    Vertex A = new Vertex("A");
    Vertex B = new Vertex("B");
    Vertex C = new Vertex("C");
    Vertex D = new Vertex("D");
    g.addVertex(A);
    g.addVertex(B);
    g.addVertex(C);
    g.addVertex(D);
    g.addEdge(A, B);
    g.addEdge(B, C);
    g.addEdge(C, A);
    g.addEdge(D, C);
    System.out.println("hasCycle: " + g.hasCycle());
  }
}
