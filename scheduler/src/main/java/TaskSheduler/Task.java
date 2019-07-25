package TaskSheduler;


import javax.sound.sampled.*;
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
    private String action;
    private GregorianCalendar data;
    private String message;
    private String description;
    private Boolean status = true;
    private Sheduler sheduler = new Sheduler();
    public Task(){

    }

    public Task(long id) {
        this.id = id;
        GregorianCalendar data = new GregorianCalendar();
        data.add(Calendar.SECOND, 30);
        this.data=data;
    }

    public Task(long id, String action, GregorianCalendar data, String description) {
        this.id = id;
        this.action = action;
        this.data = data;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }

    public GregorianCalendar getData() {
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
    public void setAction(String action) {
        this.action = action;
    }

    @XmlElement
    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement
    public void setData(GregorianCalendar data) {
        this.data = data;
    }
    @XmlElement
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants._DATAFORMAT);
        String result="";
        try {
            if (Constants._PRINTMESSAGEACTION.equals(action)) {
                result = id + Constants._TASKNAMETOSTRING + description + Constants._TASKDATETOSTRING + dateFormat.format(data.getTime()) + Constants._TASKACTIONTOSTRING + action + Constants._TASKMESSAGETOSTRING + message;
            } else if (Constants._PLAYSOUNDACTION.equals(action))
                result= id+ Constants._TASKNAMETOSTRING + description + Constants._TASKDATETOSTRING + dateFormat.format(data.getTime()) + Constants._TASKPLAYSOUNDACTIONTOSTRING;
        } catch (NullPointerException e) {
            result= id+ Constants._TASKNAMETOSTRING + description + Constants._TASKDATETOSTRING + dateFormat.format(data.getTime()) + Constants._TASKACTIONNOSTATUSTOSTRING;
        }
        return result;
    }

    public synchronized void doTask(String action) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (Constants._PRINTMESSAGEACTION.equals(action)) {
            System.out.println(message);
        }
        if (Constants._PLAYSOUNDACTION.equals(action)) {
            Clip clipSound;
            File clap = new File(Constants._SOUNDFILEPATH);
            AudioFileFormat aff = AudioSystem.getAudioFileFormat(clap);
            AudioFormat af = aff.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, af);
            if (AudioSystem.isLineSupported(info)) {
                clipSound = (Clip) AudioSystem.getLine(info);
                AudioInputStream ais = AudioSystem.getAudioInputStream(clap);
                clipSound.open(ais);
                clipSound.start();
                clipSound.stop();
                clipSound.close();
            }
            else
            {
                System.exit(0);
            }
        }
    }

    public void startSheduler(){

        sheduler.setTask(this);
        sheduler.setDaemon(true);
        sheduler.start();
        //sheduler.interrupt();
    }



}
