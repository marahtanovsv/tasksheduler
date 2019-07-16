package TaskSheduler;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@XmlRootElement
public class TaskDate {
    private long idTask;
    private Parser parser = new Parser();
    private ArrayList<Task> tasks = new ArrayList<>();

    @XmlElement
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @XmlElement
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }



    public Parser getParser() {
        return parser;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void doTask(String message) throws IOException, UnsupportedAudioFileException, InterruptedException, LineUnavailableException {
        if (message.equals("message")) {
            System.out.println(message);
        }
        if (message.equals("bip")) {
            File clap = new File("beep.wav");
            Clip clip = null;

                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(clap));
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);

        }
    }

    public void viewTask() {
        if (tasks.isEmpty()) System.out.println("Нет задач");
        else {
            for (Task taskList : tasks) {
                System.out.println(taskList.toString());
            }
        }
    }

    public void loadData() {
        this.tasks = parser.readXMLToObject().getTasks();
        Comparator<Task> comparator = Comparator.comparing(obj -> obj.getId());
        Collections.sort(tasks, comparator);
        if (tasks.isEmpty()) idTask = 0;
        else {
            idTask = tasks.get(tasks.size()).getId();
        }

    }

    public boolean addTaskToTasks(Task task) {
        if (existTask(task)) return false;
        else {

            return tasks.add(task);
        }
    }

    public Task editTask(int numberTask) {
        return tasks.get(numberTask);
    }

    public boolean existTask(Task task) {
        boolean exist = false;
        for (Task tasklist : tasks) {
            if (task.getId() == tasklist.getId()) exist = true;
        }
        return exist;
    }

    public Task createTask() {
        Task task = new Task(idTask);
        boolean areAddedTasks = addTaskToTasks(task);
        if (areAddedTasks) {
            System.out.println("Залача создана!");
            task.startSheduler();
            return task;
        } else return null;
    }
}

