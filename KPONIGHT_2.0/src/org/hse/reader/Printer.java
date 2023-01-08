package org.hse.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Printer {

    public static void printArray(ArrayList<Node> arr) {
        for (Node n : arr) {
            if (n != null) {
                System.out.println(n.getLabel());
            }
        }
    }

    public static void printArrayContent(File file_input, File file_output,
                                                                FileWriter writer) throws IOException {
        String lineSeparator = System.getProperty("line.separator");
        FileReader fileReader = new FileReader(file_input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            writer.write(line);
            writer.write(lineSeparator);
        }
    }

    public static void printGraph(Graph graph) {
        for (var i : graph.getNodes()) {
            System.out.print("File ");
            System.out.print(i.getLabel());
            System.out.println(" has a dominant files: ");
            Printer.printArray(i.getAdjacencyList());
        }
    }

    public static void printResultFile(Map<Integer, ArrayList<File>> result_Massiv, Graph graph,
                                       File resultFile, FileWriter resultWriter) throws IOException {
        for (int l = graph.getMax_level(); l >= 1; --l) {
            if (result_Massiv.containsKey(l)) {
                for (var j : result_Massiv.get(l)) {
                    System.out.println(j.getName());
                    Printer.printArrayContent(j, resultFile, resultWriter);
                }
            }
        }
    }

    public static void printGraphReverse(Graph graph) {
        for (var i : graph.getNodes()) {
            System.out.print("File ");
            System.out.print(i.getLabel());
            System.out.println(" has a slave files: ");
            Printer.printArray(i.getAdjacencyList());
        }

    }

    public static void printFileList(ArrayList<File> fileList, Map<String,File> mapFileList) {
        for (File file : fileList) {
            System.out.println(file.getAbsolutePath());
            mapFileList.put(file.getName(), file);
        }
    }
}
