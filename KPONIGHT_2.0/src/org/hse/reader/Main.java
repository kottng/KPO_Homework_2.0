package org.hse.reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static final Map<String, Node> mapNodes = new HashMap<String, Node>(); // словарь точек и
    // их названий(названия файлов)
    private static final Map<Integer, ArrayList<File>> result_Massiv = new HashMap<>(); // результирующий словарь,
    // в котором уровню для вывода соответствует массив всех веришн на этом уровне
    private static final Map<String, Node> mapNodesReverse = new HashMap<String, Node>();
    private static final Graph graph = new Graph();
    private static final Graph graphReverse = new Graph(); // граф с обратным направлением связей
    private static final File resultFile = new File("/Users/D/Desktop/root_folder/resultFile.txt");

    private static final Map<String, File> mapFileList = new HashMap<String, File>(); // словарь названий файлов
    // и их местоположения

    public static void main(String[] args) throws IOException {
        // это массив всех файлов в корневой папке
        ArrayList<File> fileList = new ArrayList<>();
        System.out.println("Please, enter your way to root folder");
        Scanner in = new Scanner(System.in);
        String line = "/Users/D/Desktop/root_folder";
        line = in.nextLine();
        System.out.println(line);
        Reader.getALLFiles(new File(line), fileList);
        for (File file : fileList) {
            mapFileList.put(file.getName(), file);
        }
        FileEditor.findRequires(fileList, graph, mapNodes, graphReverse, mapNodesReverse);

        //проверка того, что граф ацикличен
        if (graph.hasCycle()) {
            System.out.println("This tree of files have a cycle, We are sorry: we cant do this operation");
            try {
                throw new Exception("This tree of files have a cycle, We are sorry: we cant do this operation");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            //создание нового файла для вывода конкатенации файлов согласно порядку, заданному в задании
            if (!resultFile.exists()) {
                resultFile.createNewFile();
            }
            FileWriter resultWriter = new FileWriter(resultFile);
            System.out.println("This tree of files have no a cycle, We are pleasure to execute your request");
            Graph.findAllHeights(graph, mapNodes, mapNodesReverse);

            FileEditor.makeResultMassiv(mapFileList, mapNodesReverse, result_Massiv);
            Printer.printResultFile(result_Massiv, graph, resultFile, resultWriter);
            //эпилог
            resultWriter.flush();
            resultWriter.close();
        }
    }
}
