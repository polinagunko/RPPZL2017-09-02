package by.it.group573602.badey.lesson10;

import java.util.*;

/**
 * Created by User on 08.01.2018.
 */
public class UndirectedGraph {
    private Map<String, List<String>> adjacencyList;

    public UndirectedGraph() {
        adjacencyList = new HashMap<>();
    }

    public boolean hasVertex(String vertexName){
        return adjacencyList.containsKey(vertexName);
    }

    public boolean hasEdge(String parentVertex, String childVertex){
        if (!hasVertex(parentVertex))
            return false;
        List<String> edges = adjacencyList.get(parentVertex);
        return Collections.binarySearch(edges, childVertex) != -1;
    }

    public void addVertex(String vertexName){
        if (!hasVertex(vertexName))
            adjacencyList.put(vertexName, new ArrayList<>());
    }

    public void addEdge(String parentVertex, String childVertex){
        if (!hasVertex(parentVertex)) addVertex(parentVertex);
        if (!hasVertex(childVertex)) addVertex(childVertex);
        List<String> firstEdge = adjacencyList.get(parentVertex);
        List<String> secondEdge = adjacencyList.get(childVertex);
        firstEdge.add(childVertex);
        secondEdge.add(parentVertex);
        Collections.sort(firstEdge);
        Collections.sort(secondEdge);
    }

    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }
}
