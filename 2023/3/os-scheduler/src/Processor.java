import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

public class Processor {
    private String name;
    private List<Task> tasks;

    public Processor(String name) {
        this.tasks = new ArrayList<>();
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        return format("{0}\n{1}\n", this.name,
                this.tasks.stream().map(Task::toString)
                        .collect(Collectors.joining("\n")));
    }
}
