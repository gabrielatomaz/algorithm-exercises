import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

public class FileUtils {
    public static void addFile(List<Processor> processors, String filePath) throws IOException {
        var writer = new FileWriter(format("src/{0}", filePath));
        for(var processor: processors) {
            writer.write(processor.toString() + System.lineSeparator());
        }
        writer.close();
    }

    public static List<Task> getTasks(String filePath) {
        var tasks = new ArrayList<Task>();
        try (var lines = Files.lines(Paths.get(format("src/{0}", filePath)))) {
            var content = lines.collect(Collectors.joining(System.lineSeparator()));
            var tasksText = content.split("\\n");
            for (var taskText : tasksText) {
                var tasksDetailText = taskText.split(" ");
                var id = tasksDetailText[0];
                var executionTime = Integer.parseInt(tasksDetailText[1].trim());
                var task = new Task(id, executionTime);
                tasks.add(task);
            }
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
