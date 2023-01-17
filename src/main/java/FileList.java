import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record FileList(String folder) {
    public List<String> getListOfFiles() {
        return Stream.of(new File(folder).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }
}
