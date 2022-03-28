package commands;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static src.App.CURRENT_PATH;

public class CdCommand {
    private Path path;

    public CdCommand(String[] params, Path oldPath) throws IOException {
        path = Paths.get(params[0]);
        path = combinePath(oldPath).normalize();
        if (!Files.exists(path)){
            path = oldPath;
        }
        saveNewPath();
    }

    private void saveNewPath() throws IOException {
        FileOutputStream saveNewPathFile = new FileOutputStream(CURRENT_PATH);
        saveNewPathFile.write(this.path.toString().getBytes(StandardCharsets.UTF_8));
        saveNewPathFile.close();
    }

    private Path combinePath(Path oldPath){
        return oldPath.resolve(path);
    }

    public Path getPath(){
        return path;
    }
}
