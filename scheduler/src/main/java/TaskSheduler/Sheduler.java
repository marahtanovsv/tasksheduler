package TaskSheduler;

import java.util.Date;
import java.util.GregorianCalendar;

public class Sheduler extends Thread {

    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        while (task.getStatus()) {
            Date currentDate = new GregorianCalendar().getTime();
            if (currentDate.after(task.getData().getTime())) {
                String doing = task.getAction();
                if (!doing.isEmpty()) {
                    try {
                        task.doTask(doing);
                        task.setStatus(false);
                    } catch (Exception e) {
                        System.out.println(Constants._ERROR);
                    }
                }
            }
        }
    }
}
