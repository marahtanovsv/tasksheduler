package TaskSheduler;


import java.util.Calendar;

import java.util.GregorianCalendar;

public class Task {
    private String doing ;
    private Calendar data;
    private String message;
    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public String getDoing() {
        return doing;
    }

    public void setDoing(String doing) {
        this.doing = doing;
    }



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
