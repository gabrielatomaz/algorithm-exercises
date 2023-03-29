
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.text.MessageFormat.format;

public class App {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var nodes = new ArrayList<Node>();

        System.out.println("Rodar grafo de teste?\n" +
                "1 - Sim \n"
                + "2 - Não\n");
        var shouldRunDefaultGraph = scanner.nextInt();

        if (shouldRunDefaultGraph == 1) {
            var nodeA = new Node("A");
            var nodeB = new Node("B");
            var nodeC = new Node("C");
            var nodeD = new Node("D");
            var nodeE = new Node("E");

            nodeA.addAdjacentNode(nodeB, 5);
            nodeA.addAdjacentNode(nodeC, 2);

            nodeB.addAdjacentNode(nodeC, 4);
            nodeB.addAdjacentNode(nodeD, 8);
            nodeB.addAdjacentNode(nodeE, 7);

            nodeC.addAdjacentNode(nodeE, 3);
            nodeD.addAdjacentNode(nodeE, 2);

            var nodesList = List.of(nodeA, nodeB, nodeC, nodeD, nodeE);
            nodes.addAll(nodesList);
        }

        if (shouldRunDefaultGraph == 2) {
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
        }

        var graph = new Graph(nodes);

        System.out.println("\n");
        graph.printAdjacentNodesAsList();
        System.out.println("\n");

        System.out.println("Qual algoritmo você deseja rodar?" +
                "\n1. Dijkstra" +
                "\n2. Kruskal\n");
        var algorithm = scanner.nextInt();

        switch (algorithm) {
            case 1:
                System.out.println("Qual o nome do vértice de início?");
                var firstNodeName = scanner.next();

                System.out.println("\n");
                graph.generateShortestPath(firstNodeName);
                System.out.println("\n");

                break;
            case 2:
                System.out.println("\n");
                graph.generateMiniumSpanningTree();
                System.out.println("\n");

                break;
            default:
                System.out.println("Opção inválida.");

                break;
        }

        scanner.close();
    }
}
