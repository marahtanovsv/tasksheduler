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
public class Task{
    private long id;
    private String doing ;
    private Calendar data;
    private String message;
    private String description;
    private Sheduler sheduler = new Sheduler();
    private Boolean status = true;


    public Task(long id) {
        this.id = id;
        GregorianCalendar data = new GregorianCalendar();
        data.add(Calendar.HOUR, 1);
        data.add(Calendar.MINUTE, 2);
        this.data=data;
    }

    public Task(long id, String doing, Calendar data, String description) {
        this.id = id;
        this.doing = doing;
        this.data = data;
        this.description = description;
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

    public Boolean getStatus() {
        return status;
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
    @XmlElement
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss ");
        String result="";
        try {
            if (doing.equals("Message")) {
                result = id + ") Имя задачи " + description + " время запуска задачи: " + dateFormat.format(data.getTime()) + " Действие " + doing + " Выводимое сообщение " + message;
            } else if (doing.equals("bip"))
                result= id+") Имя задачи " + description + " время запуска задачи: " + dateFormat.format(data.getTime()) + "Действие воспроизвести аудио файл";
        } catch (NullPointerException e) {
            result= id+") Имя задачи " + description + " время запуска задачи: " + dateFormat.format(data.getTime()) + " Действие не задано";
        }
        return result;
    }


    public void doTask(String message) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        if ("message".equals(message)) {
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
    public void startSheduler(){
        sheduler.setTask(this);
        sheduler.setDaemon(true);
        sheduler.start();
    }



}
