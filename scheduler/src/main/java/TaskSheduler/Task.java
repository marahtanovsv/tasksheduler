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
    private Boolean isInProgress = true;
    private Scheduler sheduler = new Scheduler(); //read Callable and Future, apply one of them to run code async
    //JDBC
    public Task(){

    }

    public Task(long id) {
        this.id = id;
        GregorianCalendar data = new GregorianCalendar();
        data.add(Calendar.SECOND, 30);
        this.data=data;
    }

    public long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public Boolean isInProgress() {
        return isInProgress;
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
    public void setStatus(Boolean isInProgress) {
        this.isInProgress = isInProgress;
    }

    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATA_FORMAT);
        String result="";
        try {
            if (Constants.PRINT_MESSAGE_ACTION.equals(action)) {//TODO: StringBuffer/StringBuilder
                result = id + Constants.TASK_NAME_TO_STRING + description + Constants.TASK_DATE_TO_STRING + dateFormat.format(data.getTime()) + Constants.TASK_ACTION_TO_STRING + action + Constants.TASK_MESSAGE_TO_STRING + message;
            } else if (Constants.PLAY_SOUND_ACTION.equals(action))
                result= id + Constants.TASK_NAME_TO_STRING + description + Constants.TASK_DATE_TO_STRING + dateFormat.format(data.getTime()) + Constants.TASK_PLAY_SOUND_ACTION_TO_STRING;
        } catch (NullPointerException e) {
            result= id + Constants.TASK_NAME_TO_STRING + description + Constants.TASK_DATE_TO_STRING + dateFormat.format(data.getTime()) + Constants.TASK_ACTION_NO_STATUS_TO_STRING;
        }
        return result;
    }

    public void doTask(String action) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (Constants.PRINT_MESSAGE_ACTION.equals(action)) {
            System.out.println(message);
        }
        if (Constants.PLAY_SOUND_ACTION.equals(action)) {
            Clip clipSound;
            File clap = new File(Constants.SOUND_FILE_PATH);
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
    }
}
