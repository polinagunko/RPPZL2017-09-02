package by.it.group573602.korneenko.lesson11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.*;

public class A_Deikstra {
    private static int z, size = 8;

             public static void dijkstra(Graph g, int s, long[] prio, int[] pred) {
                try {
                        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
                        System.out.print("Начало(0): ");
                        String line = buf.readLine();
                       s = Integer.parseInt(line);
                        System.out.print("Конец(7): ");
                        line = buf.readLine();
                        z = Integer.parseInt(line);
                        buf.close();
                    } catch (Exception e) {
                        System.err.println(e.toString());
                        System.exit(0);
                    }
               Arrays.fill(pred, -1);
               Arrays.fill(prio, INF);
                prio[s] = 0;
                Queue<QItem> q = new PriorityQueue<QItem>();
                q.add(new QItem(0, s));
                while (!q.isEmpty()) {
                        QItem cur = q.poll();
                        if (cur.prio != prio[cur.v]) {
                                continue;
                            }

                                for (Edge e : g.nodeEdges[cur.v]) {
                                long nprio = prio[cur.v] + e.cost;
                                    if (prio[e.t] > nprio) {
                                        prio[e.t] = nprio;
                                        pred[e.t] = cur.v;
                                        q.add(new QItem(nprio, e.t));
                                    }
                            }
                    }
            }

             public static final long INF = Long.MAX_VALUE / 10;

             public static class QItem implements Comparable<QItem> {
         long prio;
         int v;

                 public QItem(long prio, int v) {
                        this.prio = prio;
                       this.v = v;
                    }

                 public int compareTo(QItem q) {
                        return Long.compare(prio, q.prio);
                    }
     }

             public static class Edge {
         public int s, t, cost;

                 public Edge(int s, int t, int cost) {
                        this.s = s;
                        this.t = t;
                        this.cost = cost;
                    }
     }

             public static class Graph {
         public final int n;
         public List<Edge>[] nodeEdges;

                 public Graph(int n) {
                        this.n = n;
                        nodeEdges = new List[n];
                        for (int i = 0; i < n; i++) {
                                nodeEdges[i] = new ArrayList<>();
                            }
                    }

                 void addEdge(int s, int t, int cost) {
                        nodeEdges[s].add(new Edge(s, t, cost));
                    }
     }


             public static void printPath(int[] map, int point) {
                StringBuilder result = new StringBuilder();
                while (true) {
                        result.insert(0, point);
                        point = map[point];
                        if (point < 0) {
                                break;
                            }
                        result.insert(0, ">");
                    }
                System.out.print(result);
           }

             static final int arr[][] = new int[size][size];

             public static void ReadFile() {
                try {
                        String root = System.getProperty("user.dir") + "/src/";
                        RandomAccessFile f = new RandomAccessFile(root + "by/it/group573602/korneenko/lesson11/array.dat", "r");
                        String s;
                        int a[] = new int[size * size];
                        int j = 0, i = 0, p = 0;
                        while ((s = f.readLine()) != null) {
                                StringTokenizer st = new StringTokenizer(s);
                                while (st.hasMoreTokens()) {
                                        int k = Integer.parseInt(st.nextToken());
                                        a[p] = k;
                                        System.out.print(a[p] + " ");
                                        p++;
                                    }
                                System.out.println();
                            }
                        f.close();
                        p = 0;
                        for (i = 0; i < arr.length; i++) {
                                for (j = 0; j < arr.length; j++) {
                                        arr[i][j] = a[p];
                                        p++;
                                    }
                            }
                    } catch (IOException e) {
                        System.err.println(e.toString());
                    }
            }

             public static void main(String[] args) {
                ReadFile();
                int n = arr.length;
                Graph g = new Graph(n);
                for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0) {
                                        g.addEdge(i, j, arr[i][j]);
                                    }
                            }
                    }
                long[] path = new long[g.n];
                int[] pred = new int[g.n];
                dijkstra(g, 0, path, pred);
                System.out.println("Кратчайший путь " + path[z]);
                printPath(pred, z);
            }
}
