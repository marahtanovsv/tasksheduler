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

@XmlRootElement
public class TaskDate {
    @XmlElement
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @XmlElement
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private Parser parser = new Parser();

    public Parser getParser() {
        return parser;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private ArrayList<Task> tasks = new ArrayList<>();

    public void doTask(String message) {
        if (message.equals("message")) {
            System.out.println(message);
        }
        if (message.equals("bip")) {
            File clap = new File("beep.wav");
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(clap));
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Task> viewTask() {
        return tasks;
    }

    public void loadData() {
        this.tasks = parser.readXMLToObject().getTasks();
    }

    public boolean addTaskToTasks(Task task) {
        boolean exist = false;
        for (Task taskList : tasks) {
            if (task.getDescription().equals(taskList.getDescription())) {
                exist = true;
                break;
            }
        }
        if (exist) return false;
        else return tasks.add(task);
    }

    public Task editTask(int numberTask) {
        return tasks.get(numberTask);
    }
}

