package by.it.group573602.zadorozhnuk.lesson10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Map;

public class GraphA {
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
    static UndirectedGraph graph;
    // map of visited vertices
    static Map<String, Mark> visitedMap = new LinkedHashMap<String, Mark>();
    // time counter
    static int counter = 1;

    public static void main(String[] args) {

        graph = new UndirectedGraph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "E");
        graph.addEdge("B", "C");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("E", "F");
        graph.addEdge("F", "I");

        graph.addEdge("D", "G");
        graph.addEdge("D", "H");
        graph.addEdge("G", "H");

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
            if ((visitedMap.get(graph.letters.get(i)).post - visitedMap.get(graph.letters.get(i)).pre == 1 && graph.istoks_stok.get(graph.letters.get(i))[0] > 1)) {
                for (int j = 0; j < vm.get(graph.letters.get(i)).size(); j++) {
                    if (visitedMap.get(vm.get(graph.letters.get(i)).get(j)).pre - visitedMap.get(graph.letters.get(i)).pre != -1) {
                        System.out.println(vm.get(graph.letters.get(i)).get(j) + " " + graph.letters.get(i) + " обратная");
                    }
                    ;
                }
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
