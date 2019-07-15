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
            if (currentDate.equals(task.getData())) {
                String doing = task.getDoing();
                if (!doing.isEmpty()) {
                    task.doTask(doing);
                    task.setStatus(false);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
