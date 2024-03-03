package org.example.graph.dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Solution {

  private static int networkDelayTime(int[][] times, int N, int K) {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] time : times) {
      int src = time[0];
      int dest = time[1];
      int delayTime = time[2];
      graph.putIfAbsent(src, new LinkedList<>());
      graph.get(src).add(new int[]{dest, delayTime});
    }

    Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    minHeap.add(new int[]{K, 0});
    Set<Integer> visited = new HashSet<>();
    int totalDelayTime = 0;

    while (!minHeap.isEmpty()) {
      int[] head = minHeap.poll();
      int src = head[0];
      int srcDelayTime = head[1];
      totalDelayTime = srcDelayTime;

      if (visited.contains(src)) continue;
      visited.add(src);

      if (!graph.containsKey(src)) continue;

      for (int[] edge : graph.get(src)) {
        int dest = edge[0];
        int destDelayTime = edge[1];
        minHeap.add(new int[]{dest, srcDelayTime + destDelayTime});
      }
    }

    return visited.size() == N ? totalDelayTime : -1;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    sc.nextLine();
    int[][] times;

    for (int i = 0; i < T; i++) {
      String[] s = sc.nextLine().split(" ");
      int N = Integer.parseInt(s[0]);
      int E = Integer.parseInt(s[1]);
      int K = Integer.parseInt(s[2]);
      times = new int[E][3];

      for (int j = 0; j < E; j++) {
        s = sc.nextLine().split(" ");
        times[j][0] = Integer.parseInt(s[0]);
        times[j][1] = Integer.parseInt(s[1]);
        times[j][2] = Integer.parseInt(s[2]);
      }

      System.out.println("Output : " + networkDelayTime(times, N, K));
    }
  }
}
