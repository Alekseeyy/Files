package Потоки_Ввода_Вывода_2;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Main {

    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(60, 10, 3, 234.65);
        GameProgress game2 = new GameProgress(70, 13, 5, 256.87);
        GameProgress game3 = new GameProgress(80, 15, 7, 276.73);

        saveGame("C://Games//savegames//game1.dat", game1);
        saveGame("C://Games//savegames//game2.dat", game2);
        saveGame("C://Games//savegames//game3.dat", game3);

        List<String> list = new ArrayList<>();
        list.add("C://Games//savegames//game1.dat");
        list.add("C://Games//savegames//game2.dat");
        list.add("C://Games//savegames//game3.dat");

        zipFiles("C://Games//savegames//zip.zip", list);

        File game1Dat = new File("C://Games//savegames//game1.dat");
        File game2Dat = new File("C://Games//savegames//game2.dat");
        File game3Dat = new File("C://Games//savegames//game3.dat");

        if (game1Dat.delete()) System.out.println("Файл 'game1.dat' удален");
        if (game2Dat.delete()) System.out.println("Файл 'game2.dat' удален");
        if (game3Dat.delete()) System.out.println("Файл 'game3.dat' удален");
    }

    private static void saveGame(String str, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(str);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String str, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(str))) {
            for (String arr : list) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
