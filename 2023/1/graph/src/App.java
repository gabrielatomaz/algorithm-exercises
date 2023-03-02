import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var totalNodes = 0;
        var invalidTotalNodesSizeCondition = true;
        while (invalidTotalNodesSizeCondition) {
            System.out.println("Quantos vértices você quer inserir?");
            totalNodes = scanner.nextInt();

            invalidTotalNodesSizeCondition = totalNodes <= 0 || totalNodes >= 20;
            
            if (invalidTotalNodesSizeCondition) {
                System.out.println("Tamanho inválido. Insira um valor positivo menor ou igual a 20.");
            }
        }

        var nodes = new ArrayList<Node>();
        for (var i = 0; i < totalNodes; i++) {
            System.out.println("Insira o nome do seu vértice:");
            var nodeName = scanner.next();

            nodes.add(new Node(nodeName));
        }

        for (var node : nodes) {
            var totalAdjacentsNodes = -1;
            var invalidTotalAdjacentNodesSizeCondition = true;
            while (invalidTotalAdjacentNodesSizeCondition) {
                invalidTotalAdjacentNodesSizeCondition = totalAdjacentsNodes >= totalNodes
                && totalAdjacentsNodes >= 0;
                if (totalAdjacentsNodes >= totalNodes) {
                    System.out.println(
                            "O número de vértices adjacentes não pode ser negativo e maior do que o número de vértices total."
                                    + " Por favor, insira um valor válido.");
                }

                var printMessage = format("Quantos vértices adjacentes você gostaria de adicionar ao vértice {0}",
                        node.getName());
                System.out.println(printMessage);
                totalAdjacentsNodes = scanner.nextInt();
            }

            for (int i = 0; i < totalAdjacentsNodes; i++) {
                System.out.println("Qual o nome do adjacente vértice que você gostaria de adicionar?");
                var nodeName = scanner.next();
                var adjacentNodeOptional = nodes
                        .stream()
                        .filter(adjacentNode -> nodeName.equalsIgnoreCase(adjacentNode.getName()))
                        .findFirst();

                if (adjacentNodeOptional.isPresent()) {
                    var adjacentNode = adjacentNodeOptional.get();

                    System.out.println("Qual a distância do vértice?");
                    var distance = scanner.nextInt();

                    node.addAdjacentNode(adjacentNode, distance);
                } else {
                    System.out.println("Nenhum vértice foi encontrado com esse nome.");
                }
            }
        }

        System.out.println("Qual o nome do vértice de início?");
        var firstNodeName = scanner.next();
        System.out.println("\n");

        var firstNode = nodes
                .stream()
                .filter(node -> firstNodeName.equalsIgnoreCase(node.getName()))
                .findFirst()
                .get();
                
        var dijkstra = new Dijkstra();
        dijkstra.calculateShortestPath(firstNode);
        dijkstra.printPaths(nodes);
        System.out.println("\n");
        dijkstra.printShortestPaths(nodes);

        scanner.close();
    }
}
