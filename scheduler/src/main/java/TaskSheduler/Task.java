package TaskSheduler;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
@XmlRootElement
public class Task {
    private long id;
    private String doing ;
    private Calendar data;
    private String message;
    private String description;
    private Sheduler sheduler = new Sheduler();

    public  Task() {
        data = new GregorianCalendar();
        id++;
    }
    public  Task(Calendar data) {
        id++;
        this.data = data;
    }
    public  Task(Calendar data, String message) {
        this.data = data;
        this.message = message;
        id++;
    }


    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }
    public String getDoing() {
        return doing;
    }
    public String getMessage() {
        return message;
    }
    public Calendar getData() {
        return data;
    }
    @XmlAttribute
    public void setId(long id) {
        this.id = id;
    }
    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }
    @XmlElement
    public void setDoing(String doing) {
        this.doing = doing;
    }
    @XmlElement
    public void setMessage(String message) {
        this.message = message;
    }
    @XmlElement
    public void setData(Calendar data) {
        this.data = data;
    }
    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss ");
        String result="";
        try {
            if (doing.equals("Message"))
                result= id+") Имя задачи " + description + " время запуска задачи: " + dateFormat.format(data.getTime()) + " Действие " + doing + " Выводимое сообщение " + message;
            else if (doing.equals("bip"))
                result= id+") Имя задачи " + description + " время запуска задачи: " + dateFormat.format(data.getTime()) + "Действие воспроизвести аудио файл";
        } catch (NullPointerException e) {
            result= id+") Имя задачи " +description + " время запуска задачи: " + dateFormat.format(data.getTime()) + " Действие не задано";
        }
        return result;
    }


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
    public void startSheduler(){
        sheduler.setTask(this);
        sheduler.setDaemon(true);
        sheduler.run();
    }
}
