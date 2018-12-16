package ru.basejava.storage;

import java.io.File;

public class ShowFilesWithRecursion {

    public static void main(String[] args) {
        File dir = new File("./src");
        showFiles(dir);
    }

    public static void showFiles(File dir) {
        File[] files = dir.listFiles();
        if(files != null) {
            for(File file : files) {
                if(file.isFile()) {
                    System.out.println(file.getName());
                } else {
                    showFiles(file);
                }
            }
        }
    }
}
