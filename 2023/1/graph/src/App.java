import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var edges = new ArrayList<Edge>();

        for (int i = 1; i <= 20; i++) {
            System.out.println(format("Vértice {0}\n", i));

            System.out.println("Insira a origem do vértice: ");
            var source = scanner.nextInt();

            System.out.println("Insira o destino do vértice: ");
            var destination = scanner.nextInt();

            System.out.println("Insira o peso do vértice: ");
            var weigth = scanner.nextInt();

            if (weigth < 0 || source < 0) {
                System.out.println("Por favor, insira um valor válido.");
                i--;
                continue;
            }

            var edge = new Edge(source, destination, weigth);
            edges.add(edge);

            if (i < 20) {
                System.out.println("Gostaria de adicionar mais um vértice?\n"
                        + "1 - Sim\n"
                        + "2 - Não");
                var loop = scanner.nextInt();

                if (loop == 2)
                    break;
            }
        }

        scanner.close();

        var graph = new Graph(edges);
        graph.populateNodes();
        graph.print();
    }
}
