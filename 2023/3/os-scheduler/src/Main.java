import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       var fileName = args[0];
       var numberOfProcessors = Integer.parseInt(args[1]);

        var tasks = FileUtils.getTasks(fileName);
        var scheduler = new Scheduler(numberOfProcessors, tasks);

        var processorsShortest = scheduler.scheduleShortestJobFirst();
        FileUtils.addFile(processorsShortest, "output_shortest.txt");

        var processorsBiggest = scheduler.scheduleBiggestJobFirst();
        FileUtils.addFile(processorsBiggest, "output_biggest.txt");
    }
}