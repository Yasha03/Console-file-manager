package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.util.Date;

public class DirCommand {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public void readFiles(File directory, int tabs) throws IOException {
        if (directory.isDirectory()){
            for (File file : directory.listFiles()) {
                if(file.isFile()){
                    printFileInfo(file, tabs);
                }else {
                    printDirectoryInfo(file, tabs);
                    readFiles(file, tabs + 1);
                }
            }
        }
    }

    public void printFileInfo(File file, int tabs) throws IOException {
        for (int i = 0; i < tabs; i++)
            System.out.print("      ");
        System.out.print(ANSI_RED + "FILE: " + file.getName());
        System.out.print("  SIZE: " + getFileSizeKiloBytes(file));
        System.out.print("  OWNER: " + getFileOwner(file));
        System.out.print("  MODIFIED: " + getFileLastModified(file));

        System.out.print(ANSI_RESET + "\n");
    }

    public void printDirectoryInfo(File file, int tabs){
        for (int i = 0; i < tabs; i++)
            System.out.print("      ");
        System.out.print(ANSI_YELLOW + "DIRECTORY: " + file.getName());

        System.out.print(ANSI_RESET + "\n");
    }

    private String getFileSizeKiloBytes(File file) {
        return file.length()/1024 + " kb";
    }

    private String getFileOwner(File file) throws IOException {
        Path path = Paths.get(String.valueOf(file));
        UserPrincipal owner = Files.getOwner(path);
        String username = owner.getName();
        return username;
    }

    private String getFileLastModified(File file){
        long timestamp = file.lastModified();
        return String.valueOf(new Date(timestamp));
    }




}
