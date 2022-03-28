package commands;

import java.io.File;
import java.nio.file.Path;

public class CopyCommand {
    private Path path;
    public CopyCommand(Path path) {
        this.path = path;
    }

    public boolean copyFile(Path oldPath, Path newPath){
        oldPath = path.resolve(oldPath);
        newPath = path.resolve(newPath);
        File oldFile = new File(String.valueOf(oldPath));
        File newFile = new File(String.valueOf(newPath));
        if(newFile.isDirectory()){
            newFile = new File(String.valueOf(newPath) + "/" + oldFile.getName());
        }
        return oldFile.renameTo(newFile);
    }
}
