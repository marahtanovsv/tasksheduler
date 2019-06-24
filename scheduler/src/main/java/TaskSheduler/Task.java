package TaskSheduler;


import java.util.Calendar;

import java.util.GregorianCalendar;

public class Task {
    public String getDoing() {
        return doing;
    }

    private String doing ;

    public void setDoing(String doing) {
        this.doing = doing;
    }

    private Calendar data;
    private String message;

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {

        this.message = message;
    }


    public void setData(Calendar data) {
        this.data = data;
    }

    public Calendar getData() {

        return data;
    }

    public Task() {
        data = new GregorianCalendar();
    }

    public Task(Calendar data) {

        this.data = data;
    }

    public Task(Calendar data, String message) {
        this.data = data;
        this.message = message;
    }

}
