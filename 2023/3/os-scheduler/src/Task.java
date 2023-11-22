import static java.text.MessageFormat.format;

public class Task implements Comparable<Task> {
    private String id;
    private int executionTime;
    private int endTime;

    private int startTime;

    public Task(String id, int executionTime) {
        this.id = id;
        this.executionTime = executionTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    @Override
    public int compareTo(Task task) {
        return Integer.compare(executionTime, task.executionTime);
    }

    @Override
    public String toString() {
        return format("{0};{1};{2}", this.id, this.startTime, this.endTime);
    }
}
