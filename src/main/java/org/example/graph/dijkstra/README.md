## Limitations of Dijkstra Algorithm
The following are some limitations of the Dijkstra Algorithm:
- The Dijkstra algorithm does not work when an edge has negative values.
- For cyclic graphs, the algorithm does not evaluate the shortest path. Hence, for the cyclic graphs, it is not recommended to use the Dijkstra Algorithm.

## Usages of Dijkstra Algorithm
A few prominent usages of the Dijkstra algorithm are:
- The algorithm is used by Google maps.
- The algorithm is used to find the distance between two locations.
- In IP routing also, this algorithm is used to discover the shortest path.

## Solution Sample Input
3
4 3 2
2 1 1
2 3 1
3 4 1
2 1 1
1 2 1
2 1 2
1 2 1

## Solution Sample Output
Output : 2
Output : 1
Output : -1

## Graph (Directed) Sample Input
3
1 5 3 2
2 1 1
2 3 1
3 4 1
1 3 1 1
1 2 1
1 3 1 2
1 2 1

## Graph (Directed) Sample Output
The shorted path from node :
2 to 0 is 2147483647
2 to 1 is 1
2 to 2 is 0
2 to 3 is 1
2 to 4 is 2
The shorted path from node :
1 to 0 is 2147483647
1 to 1 is 0
1 to 2 is 1
The shorted path from node :
2 to 0 is 2147483647
2 to 1 is 2147483647
2 to 2 is 0

## Graph (Undirected) Sample Input
3
0 9 14 0
0 1 4
0 7 8
1 2 8
1 7 11
2 3 7
2 8 2
2 5 4
3 4 9
3 5 14
4 5 10
5 6 2
6 7 1
6 8 6
7 8 7
0 5 6 0
0 1 9
0 2 6
0 3 5
0 4 3
2 1 2
2 3 4
0 9 17 0
0 1 3
0 7 7
1 2 7
1 7 10
1 8 4
2 3 6
2 5 2
2 8 1
3 4 8
3 5 13
3 8 3
4 5 9
5 6 4
5 8 5
6 7 2
6 8 5
7 8 6

## Graph (Undirected) Sample Output
The shorted path from node :
0 to 0 is 0
0 to 1 is 4
0 to 2 is 12
0 to 3 is 19
0 to 4 is 21
0 to 5 is 11
0 to 6 is 9
0 to 7 is 8
0 to 8 is 14
The shorted path from node :
0 to 0 is 0
0 to 1 is 8
0 to 2 is 6
0 to 3 is 5
0 to 4 is 3
The shorted path from node :
0 to 0 is 0
0 to 1 is 3
0 to 2 is 8
0 to 3 is 10
0 to 4 is 18
0 to 5 is 10
0 to 6 is 9
0 to 7 is 7
0 to 8 is 7
