package by.it.group573601.Stephanovich.lesson10;

// A Java program to print topological sorting of a DAG

import java.util.*;

// This class represents a directed graph using adjacency
// list representation
public class GraphB {
    private int V; // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency List

    //Constructor
    GraphB(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean visited[],
                             Stack stack) {
// Mark the current node as visited.
        visited[v] = true;
        Integer i;

// Recur for all the vertices adjacent to this
// vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

// Push current vertex to stack which stores result
        stack.push(new Integer(v));
    }

    // The function to do Topological Sort. It uses
// recursive topologicalSortUtil()
    void topologicalSort() {
        Stack stack = new Stack();

// Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

// Call the recursive helper function to store
// Topological Sort starting from all vertices
// one by one
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

// Print contents of stack
        while (stack.empty() == false)
            System.out.print(stack.pop() + " ");
    }

    // Driver method
    public static void main(String args[]) {
// Create a graph given in the above diagram
        GraphB g = new GraphB(8);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);

        System.out.println("Following is a Topological " +
                "sort of the given graph");
        g.topologicalSort();
    }
}