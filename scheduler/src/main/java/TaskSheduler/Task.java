package TaskSheduler;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.annotation.*;
import java.util.GregorianCalendar;
@XmlRootElement(name = "Task")
@XmlType(propOrder = {"description", "data", "doing","message"})
public class Task {



    private long id=-1;
    private String doing ;
    private Calendar data;
    private String message;
    private String description;

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
    @XmlAttribute
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

    public void setDescription(String description) {
        this.description = description;
    }
    public void setDoing(String doing) {
        this.doing = doing;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setData(Calendar data) {
        this.data = data;
    }
    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy  HH:mm:ss ");
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

}
