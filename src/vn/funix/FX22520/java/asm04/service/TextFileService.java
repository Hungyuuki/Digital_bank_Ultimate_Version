package vn.funix.FX22520.java.asm04.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static vn.funix.FX22520.java.asm04.staticValue.Message.COMMA_DELIMITER;

/**
 * Ở lớp này định nghĩa đọc 1 file txt, 1 file có 1 list các dòng, mỗi dòng là 1 list các String.
 * Cách làm là tách các dòng ra theo dấu COMA_DELIMITER, sau đó đưa vào 1 danh sách
 */
public class TextFileService {
    public static List<List<String>> readFile(String fileName) throws IOException {
        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            List<List<String>> objects = new ArrayList<>();
            String line = file.readLine();
            while (line != null) {
                List<String> subList = List.of(line.split(COMMA_DELIMITER));
                objects.add(subList);
                line = file.readLine();
                System.out.printf("%15s | %20s | %10s | %10s", subList.get(0), subList.get(1), "", " \n");
            }
            return objects;
        }
        catch (EOFException e) {
            return new ArrayList<>();
        }
    }
}
