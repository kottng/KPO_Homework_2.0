package org.hse.reader;

import java.util.ArrayList;

public class Node {

    private String label;
    private int level = 0;
    private boolean beingVisited;
    private boolean visited;
    private final ArrayList<Node> adjacencyList;

    public Node(String label) {
        this.label = label;
        this.adjacencyList = new ArrayList<>();
    }

    public void addNeighbor(Node adjacent) {
        this.adjacencyList.add(adjacent);
    }

    public void setBeingVisited(boolean b) {
        this.beingVisited = b;
    }

    public boolean getbeingVisited() {
        return this.beingVisited;
    }

    public ArrayList<Node> getAdjacencyList() {
        return this.adjacencyList;
    }

    public void setAdjacencyList(ArrayList<Node> arr) {
        this.adjacencyList.addAll(arr);
    }

    public boolean getVisited() {
        return this.visited;
    }

    public void setVisited(boolean b) {
        this.visited = b;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String l) {
        this.label = l;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int l) {
        this.level = l;
    }
}