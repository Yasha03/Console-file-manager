package src;

import commands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class App extends Application{
    private Path currentPath;

    public static final String CURRENT_PATH = "G:\\files\\workZone\\work other\\WORK JAVA\\itis\\2 sem\\ConsoleFileManager\\data\\CurrentPath.txt";

    public static void main(String[] args) {
        App app = new App(args);
    }

    public App(String[] args) {
        super(args);
    }

    @Override
    public void init() {
        try {
            Scanner sc = new Scanner(new File(CURRENT_PATH));
            if(sc.hasNext()) {
                this.currentPath = Paths.get(sc.nextLine());
            }
            else {
                this.currentPath = Paths.get("");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            String command;
            if(args.length == 0){
                command = "help";
            }else {
                command = args[0];
            }
            switch(command){
                case "cd":
                    if(args.length > 1){
                        String[] params = Arrays.copyOfRange(args, 1, args.length);
                        CdCommand cdCommand = new CdCommand(params, currentPath);
                        this.currentPath = cdCommand.getPath();
                    }else{
                        System.out.println("Error: enter parameters.");
                    }
                    System.out.println(this.currentPath);
                    break;
                case "dir":
                    DirCommand dirCommand = new DirCommand();
                    dirCommand.readFiles(new File(String.valueOf(currentPath)), 0);
                    break;
                case "copy":
                    if(args.length == 3){
                        CopyCommand copyCommand = new CopyCommand(currentPath);
                        if (copyCommand.copyFile(Paths.get(args[1]), Paths.get(args[2]))){
                            System.out.println("File copied");
                        }else {
                            System.out.println("File not copied");
                        }
                    }else{
                        System.out.println("Error: enter parameters.");
                    }
                    break;
                case "delete":
                    if (args.length == 2) {
                        DeleteCommand deleteCommand = new DeleteCommand(this.currentPath, Paths.get(args[1]));
                        deleteCommand.delete();
                    }else{
                        System.out.println("Error: enter parameters.");
                    }
                    break;
                default:
                    System.out.println("Unknown command");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
