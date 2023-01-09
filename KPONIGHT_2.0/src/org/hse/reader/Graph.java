package org.hse.reader;

import java.util.ArrayList;
import java.util.Map;

public class Graph {

    private final ArrayList<Node> nodes;
    private int Max_level = 1;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public static void findAllHeights(Graph graph, Map<String, Node> mapNodes, Map<String, Node> mapNodesReverse) {
        for (var i : mapNodes.keySet()) {
            if (mapNodes.get(i).getAdjacencyList().isEmpty()) {
                graph.dfs(mapNodesReverse.get(i));
            }
        }
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge(Node from, Node to) {
        from.addNeighbor(to);
    }

    public int getMax_level() {
        return Max_level;
    }

    public boolean hasCycle() {
        for (Node node : nodes) {
            if (!node.getVisited() && hasCycle(node)) {
                return true;
            }
        }
        return false;
    }

    public int dfs(Node node) {
        node.setVisited(true);
        int depth = 1;
        for (var r : node.getAdjacencyList()) {
            if (!r.getVisited()) {
                depth = Math.max(depth, dfs(r) + 1);
            }
        }
        node.setLevel(depth);
        if (Max_level < depth) {
            Max_level = depth;
        }
        return depth;
    }

    public boolean hasCycle(Node sourceNode) {
        sourceNode.setBeingVisited(true);

        for (Node neighbor : sourceNode.getAdjacencyList()) {
            if (neighbor.getbeingVisited()) {
                // backward edge exists
                return true;
            } else if (!neighbor.getVisited() && hasCycle(neighbor)) {
                return true;
            }
        }
        sourceNode.setBeingVisited(false);
        sourceNode.setVisited(true);
        return false;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}