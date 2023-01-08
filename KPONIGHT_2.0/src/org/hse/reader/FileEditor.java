package org.hse.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class FileEditor {
    public static void findRequires(ArrayList<File> arr, Graph graph, Map<String,Node> mapNode, Graph graphReverse,
                                    Map<String,Node> mapNodeReverse) throws FileNotFoundException {
        for (var i : arr) {
            Node new_elem_reverse = new Node(i.getName());
            Node new_elem = new Node(i.getName());
            mapNode.put(i.getName(), new_elem);
            mapNodeReverse.put(i.getName(), new_elem_reverse);
            graph.addNode(new_elem);
            graphReverse.addNode(new_elem_reverse);
        }

        // перебираем все файлы из списка пулла файлов
        for(File file: arr) {
            // взяли и теперь сканируем его
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                //сканируем очередную строчку
                String line = scanner.nextLine();
                String way;
                String name_of_dominante_file;
                int position_of_filename = 0;

                if (line.contains("require")) {
                    //если строчка содержит require, то файл может зависеть от других
                    way = line.substring(9, line.length()-1);
                    position_of_filename = way.lastIndexOf("/");
                    if (position_of_filename != -1){
                        //если ввод корректен
                        name_of_dominante_file = way.substring(position_of_filename + 1, way.length());
                        graph.addEdge(mapNode.get(file.getName()), mapNode.get(name_of_dominante_file));
                        graphReverse.addEdge(mapNodeReverse.get(name_of_dominante_file),
                                mapNodeReverse.get(file.getName()));
                    } else {
                        //если ввод некорректный
                        System.out.println("You've got a problem with a content of your file");
                        name_of_dominante_file = "";
                    }
                }
            }
        }
    }
    public static void makeResultMassiv(Map<String,File> mapFileList, Map<String,Node> mapNodesReverse,
                                        Map<Integer, ArrayList<File>> result_Massiv) {
        for (var k : mapNodesReverse.keySet()) {
            if (result_Massiv.containsKey(mapNodesReverse.get(k).getLevel())) {
                result_Massiv.get(mapNodesReverse.get(k).getLevel()).add(mapFileList.get(
                        mapNodesReverse.get(k).getLabel()));
            } else {
                ArrayList<File> arr = new ArrayList();
                arr.add(mapFileList.get(mapNodesReverse.get(k).getLabel()));
                result_Massiv.put(mapNodesReverse.get(k).getLevel(), arr);
            }
//            System.out.print(mapNodesReverse.get(k).getLabel());
//            System.out.print(" ");
//            System.out.println(mapNodesReverse.get(k).getLevel());
        }
    }
}
