package possiblePath;

import java.util.LinkedList;

public class Search {

    private static final String START = "A1";
    private static final String END = "B1";

    public static void main(String[] args) {
        // this graph is directional
        Graph graph = new Graph();
        graph.addEdge("A1", "BM");
        graph.addEdge("BM", "BUC");
        graph.addEdge("BUC", "BM");
        graph.addEdge("BUC", "MRO");
        graph.addEdge("MRO", "BUC");
        graph.addEdge("MRO", "RCO"); // this is the only one-way connection
        graph.addEdge("RCO", "MRO");
        graph.addEdge("RCO", "BUC");
        graph.addEdge("RCO", "BM");
        graph.addEdge("RCO", "B1");
       
        LinkedList<String> visited = new LinkedList();
        visited.add(START);
        new Search().depthFirst(graph, visited);
    }

    private void depthFirst(Graph graph, LinkedList<String> visited) {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
        // examine adjacent nodes
        for (String node : nodes) {
            if (visited.contains(node)) {
              continue;
            }
            if (node.equals(END)) {
                visited.add(node);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(END)) {
               // continue;
            	if(visited.size()>10)
            	{
            		continue;
            	}
            }
            visited.addLast(node);
            depthFirst(graph, visited);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<String> visited) {
        for (String node : visited) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
    }
}
