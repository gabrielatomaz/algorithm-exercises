import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.text.MessageFormat.format;

public class Scheduler {
    private int numberOfProcessors;
    private List<Task> tasks;

    public Scheduler(int numberOfProcessors, List<Task> tasks) {
        this.numberOfProcessors = numberOfProcessors;
        this.tasks = tasks;
    }

    public List<Processor> scheduleShortestJobFirst() {
        Collections.sort(tasks);
        return getProcessors();
    }

    public List<Processor> scheduleBiggestJobFirst() {
        tasks.sort(Collections.reverseOrder());
        return getProcessors();
    }

    private ArrayList<Processor> getProcessors() {
        var processors = new ArrayList<Processor>();
        for (int i = 0; i < numberOfProcessors; i++) {
            var processor = new Processor(format("Processador_{0}", i + 1));
            processors.add(processor);
        }

        var numberOfProcessorsIndex = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (numberOfProcessorsIndex == this.numberOfProcessors) {
                numberOfProcessorsIndex = 0;
            }
            var processor = processors.get(numberOfProcessorsIndex);
            var task = tasks.get(i);

            processor.setTask(task);
            numberOfProcessorsIndex++;
        }

        for (int i = 0; i < processors.size(); i++) {
            var processor = processors.get(i);
            for (int j = 0; j < processor.getTasks().size(); j++) {
                var task = processor.getTasks().get(j);
                var previousTask = j > 0 ? processor.getTasks().get(j - 1) : null;

                if (previousTask == null) {
                    task.setStartTime(0);
                    task.setEndTime(task.getExecutionTime());
                } else {
                    task.setStartTime(previousTask.getEndTime());
                    task.setEndTime(task.getExecutionTime() + previousTask.getEndTime());
                }
            }
        }

        return processors;
    }

}
