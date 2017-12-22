package by.it.group573601.krautsou.lesson11;

import java.util.Scanner;

public class BellmanFord {
    private int[] distances;
    private int vertices;
    public static final int MAX_VALUE = 99999;

    public BellmanFord(int vertices) {
        this.vertices = vertices;
        distances = new int[vertices + 1];
    }

    public void Evaluation(int source, int adjacencymatrix[][]) {
        for (int node = 1; node <= vertices; node++) {
            distances[node] = MAX_VALUE;
        }

        distances[source] = 0;
        for (int node = 1; node <= vertices - 1; node++) {
            for (int sourcenode = 1; sourcenode <= vertices; sourcenode++) {
                for (int destinationnode = 1; destinationnode <= vertices; destinationnode++) {
                    if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
                        if (distances[destinationnode] > distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode])
                            distances[destinationnode] = distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode];
                    }
                }
            }
        }

        for (int sourcenode = 1; sourcenode <= vertices; sourcenode++) {
            for (int destinationnode = 1; destinationnode <= vertices; destinationnode++) {
                if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
                    if (distances[destinationnode] > distances[sourcenode]
                            + adjacencymatrix[sourcenode][destinationnode])
                        System.out.println("Отрицательный цикл");
                }
            }
        }

        for (int vertex = 1; vertex <= vertices; vertex++) {
            if (distances[vertex] == MAX_VALUE) {
                System.out.println("Путь " + source + " до "
                        + vertex + " не найден ");
            } else {
                System.out.println("Дистанция  " + source + " до "
                        + vertex + " = " + distances[vertex]);
            }
        }
    }

    public static void main(String[] arg) {
        int vertices;
        int source;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Количество");
        vertices = scanner.nextInt();

        int adjacencymatrix[][] = new int[vertices + 1][vertices + 1];
        System.out.println("Матрица");
        for (int sourcenode = 1; sourcenode <= vertices; sourcenode++) {
            for (int destinationnode = 1; destinationnode <= vertices; destinationnode++) {
                adjacencymatrix[sourcenode][destinationnode] = scanner.nextInt();
                if (sourcenode == destinationnode) {
                    adjacencymatrix[sourcenode][destinationnode] = 0;
                    continue;
                }
                if (adjacencymatrix[sourcenode][destinationnode] == 0) {
                    adjacencymatrix[sourcenode][destinationnode] = MAX_VALUE;
                }
            }
        }

        System.out.println("Исходная точка");
        source = scanner.nextInt();
        BellmanFord bellmanford = new BellmanFord(vertices);
        bellmanford.Evaluation(source, adjacencymatrix);
        scanner.close();
    }
}