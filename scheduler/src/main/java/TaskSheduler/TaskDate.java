package TaskSheduler;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
@XmlRootElement(name = "TaskDate")
@XmlType(propOrder = {"tasks"})
public class TaskDate {
    private Parser parser = new Parser();
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
    public ArrayList<Task> loadData(){
        return parser.readXMLToObject();
    }
    public boolean addTaskToTasks(Task task) {
        return tasks.add(task);
    }

    public Task editTask(int numberTask) {
        return tasks.get(numberTask);
    }
}

