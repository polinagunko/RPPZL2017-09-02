package by.it.group573602.zadorozhnuk.lesson10;

import java.util.*;

public class GraphC {
    static class Mark {
        public Mark(int pre, int post) {
            this.pre = pre;
            this.post = post;
        }

        public int pre;
        public int post;
    }

    ;

    // our graph
    static DirectedGraph graph;
    // map of visited vertices
    static Map<String, Mark> visitedMap = new LinkedHashMap<String, Mark>();
    // time counter
    static int counter = 1;

    public static void main(String[] args) {

        graph = new DirectedGraph();
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "F");
        graph.addEdge("E", "F");
        graph.addEdge("F", "G");
        graph.addEdge("F", "H");

        Map<String, List<String>> vm = graph.getVertexMap();

        List<String> vertexList = new ArrayList<String>(vm.size());
        vertexList.addAll(vm.keySet());
        Collections.sort(vertexList);

        for (String v : vertexList) {
            dfs(v);
        }

        for (Map.Entry<String, Mark> entry : visitedMap.entrySet()) {
            Mark m = entry.getValue();
            System.out.format("%1$s : (%2$d, %3$d)\n", entry.getKey(), m.pre, m.post);
        }

        for (int i = 0; i < graph.letters.size(); i++) {
            if (graph.istoks_stok.get(graph.letters.get(i))[0] == 0)
                System.out.println(graph.letters.get(i) + " - Исток");
            if (graph.istoks_stok.get(graph.letters.get(i))[1] == 0) {
                System.out.println(graph.letters.get(i) + " - Сток");
            }

        }


    }

    static void dfs(String vertexName) {
        if (visitedMap.containsKey(vertexName)) return;

// set pre (time of enter)
        visitedMap.put(vertexName, new Mark(counter, -1));
        counter++;

// retrieve adjacent vertices
        Map<String, List<String>> vm = graph.getVertexMap();
        List<String> adjacentVertices = vm.get(vertexName);

        for (String v : adjacentVertices) {
            if (visitedMap.containsKey(v)) continue;
            dfs(v);
        }

// set post (time of exit)
        Mark m = visitedMap.get(vertexName);
        m.post = counter++;
    }
}
