package by.it.group573602.tumash.lesson10;

import java.util.*;

public class UndirectedGraph {

    private HashMap<String, List<String>> vertexMap = new HashMap<String, List<String>>();
    public HashMap<String, Integer[]> istoks_stok = new HashMap<String, Integer[]>();
    public ArrayList<String> letters = new ArrayList<>();

    public void addVertex(String vertexName) {
        if (!hasVertex(vertexName)) {
            vertexMap.put(vertexName, new ArrayList<String>());
        }
    }

    public boolean hasVertex(String vertexName) {
        return vertexMap.containsKey(vertexName);
    }

    public boolean hasEdge(String vertexName1, String vertexName2) {
        if (!hasVertex(vertexName1)) return false;
        List<String> edges = vertexMap.get(vertexName1);
        return Collections.binarySearch(edges, vertexName2) != -1;
    }

    public void addEdge(String vertexName1, String vertexName2) {
        int istoks = 0;
        int stoks = 0;
        if (!hasVertex(vertexName1)) {
            addVertex(vertexName1);
            stoks = 1;
            istoks_stok.put(vertexName1, new Integer[]{istoks, stoks});
            letters.add(vertexName1);
        } else if (hasVertex(vertexName1)) {
            istoks_stok.replace(vertexName1, istoks_stok.get(vertexName1), new Integer[]{istoks_stok.get(vertexName1)[0], istoks_stok.get(vertexName1)[1] + 1});
        }
        if (!hasVertex(vertexName2)) {
            istoks = 1;
            addVertex(vertexName2);

            istoks_stok.put(vertexName2, new Integer[]{istoks, stoks});
            letters.add(vertexName2);
        } else if (hasVertex(vertexName2)) {
            istoks_stok.replace(vertexName2, istoks_stok.get(vertexName2), new Integer[]{istoks_stok.get(vertexName2)[0] + 1, istoks_stok.get(vertexName2)[1]});
        }

        List<String> edges1 = vertexMap.get(vertexName1);
        List<String> edges2 = vertexMap.get(vertexName2);
        edges1.add(vertexName2);
        edges2.add(vertexName1);
        Collections.sort(edges1);
        Collections.sort(edges2);
    }

    public Map<String, List<String>> getVertexMap() {
        return vertexMap;
    }
}
