package by.it.group573602.badey.lesson10;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by User on 09.01.2018.
 */
public class B_Graphs {
    private int vertexAmount; // No. of vertices
    private LinkedList<Integer> adjacencyList[]; // Adjacency List

    //Constructor
    B_Graphs(int vertex) {
        this.vertexAmount = vertex;
        adjacencyList = new LinkedList[vertex];
        for (int i = 0; i < vertex; ++i)
            adjacencyList[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adjacencyList[v].add(w);
    }

    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean visited[],
                             Stack stack) {
// Mark the current node as visited.
        visited[v] = true;
        Integer i;

// Recur for all the vertices adjacent to this
// vertex
        Iterator<Integer> it = adjacencyList[v].iterator();
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
        boolean visited[] = new boolean[vertexAmount];
        for (int i = 0; i < vertexAmount; i++)
            visited[i] = false;

// Call the recursive helper function to store
// Topological Sort starting from all vertices
// one by one
        for (int i = 0; i < vertexAmount; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

// Print contents of stack
        while (stack.empty() == false)
            System.out.print(stack.pop() + " ");
    }

    // Driver method
    public static void main(String args[]) {
// Create a graph given in the above diagram
        B_Graphs graph = new B_Graphs(8);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);

        System.out.println("Following is a Topological " +
                "sort of the given graph");
        graph.topologicalSort();
    }
}
