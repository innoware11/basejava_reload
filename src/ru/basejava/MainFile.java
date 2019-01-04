package ru.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File(".\\src");
        showFileSystemTree(dir, "\t");
    }

    private static void showFileSystemTree(File dir, String offcet) {
        File[] files = dir.listFiles();
        Objects.requireNonNull(files, "files can't be null");
        for(File file : files) {
            if(file.isDirectory()) {
                showFileSystemTree(file, offcet + "\t");
            } else if(file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }
}
