import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Content {
    private final String fileName;
    private final FileList files;
    private Path filePath;
    private String type;


    public Content(String fileName, FileList files) {
        this.fileName = fileName;
        this.files = files;
        this.filePath = Path.of(this.files.getFilesPath().toString(), fileName);
        this.type = getContentType();
    }

    public String getContentType() {
        try {
            return Files.probeContentType(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isExists() {
        String fName = fileName;
        if (fName.charAt(0) == '/') {
            fName = fName.substring(1);
        }
        return files.isFileInList(fName);
    }

    public Path getFilePath() {
        return filePath;
    }
}
