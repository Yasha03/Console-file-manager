package commands;

import java.io.File;
import java.nio.file.Path;

public class DeleteCommand {
    private File file;

    public DeleteCommand(Path path, Path pathDelete) {
        path = path.resolve(pathDelete);
        this.file = new File(String.valueOf(path));
    }

    public void delete(){
        deleteRecursion(this.file);
    }

    public void deleteRecursion(File directory){
        if (directory.isDirectory()){
            for (File file : directory.listFiles()) {
                if(file.isFile()){
                    file.delete();
                }else {
                    deleteRecursion(file);
                }
            }
        }
        directory.delete();
    }
}
