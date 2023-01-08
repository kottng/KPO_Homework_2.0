package org.hse.reader;

import java.util.ArrayList;

public class Node {

    private String label;
    private int level = 0;
    private boolean beingVisited;
    private boolean visited;
    private ArrayList<Node> adjacencyList;

    public Node(String label) {
        this.label = label;
        this.adjacencyList = new ArrayList<>();
    }

    public void addNeighbor(Node adjacent) {
        this.adjacencyList.add(adjacent);
    }

    public void setBeingVisited(boolean b) {
        this.beingVisited=b;
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

    public void setLabel(String l) {
        this.label = l;
    }
    public String getLabel() {
        return this.label;
    }

    public void setLevel(int l) {
        this.level = l;
    }

    public int getLevel() {
        return this.level;
    }
}