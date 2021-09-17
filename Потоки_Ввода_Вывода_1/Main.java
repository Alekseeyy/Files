package Потоки_Ввода_Вывода_1;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        List<File> folderList = Arrays.asList(
                new File("C://Games"),
                new File("C://Games//src"),
                new File("C://Games//res"),
                new File("C://Games//savegames"),
                new File("C://Games//temp"),
                new File("C://Games//src//main"),
                new File("C://Games//src//test"),
                new File("C://Games//res//drawables"),
                new File("C://Games//res//vectors"),
                new File("C://Games//res//icons")
        );

        List<File> fileList = Arrays.asList(
                new File("C://Games//src//main//Main.java"),
                new File("C://Games//src//main//Utils.java"),
                new File("C://Games//temp//temp.txt")
        );

        folderList.stream()
                .forEach(folder -> {
                    if (folder.mkdir()) {
                        sb.append("Католог " + folder + " создан\n");
                    } else {
                        sb.append("Католог " + folder + " не создан\n");
                    }
                });

        fileList.stream()
                .forEach(file -> {
                    try {
                        if (file.createNewFile()) {
                            sb.append("Файл " + file + " создан\n");
                        } else {
                            sb.append("Файл " + file + " не создан\n");
                        }
                    } catch (IOException ex) {
                        sb.append(ex.getMessage() + "\n");
                    }
                });

        try (FileWriter writer = new FileWriter("C://Games//temp//temp.txt", false)) {
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException ex) {
            sb.append(ex.getMessage() + "\n");
        }

        try (FileReader reader = new FileReader("C://Games//temp//temp.txt")) {
            int i;
            while ((i = reader.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
