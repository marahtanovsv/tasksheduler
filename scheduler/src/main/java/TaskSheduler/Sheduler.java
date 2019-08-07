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
        Date currentDate = new GregorianCalendar().getTime();
        while (task.isInProgress() && currentDate.after(task.getData().getTime())) {
            String action = task.getAction();
            if (task.isInProgress() && !action.isEmpty()) {
                try {
                    task.doTask(action);
                    task.setStatus(false);
                    this.interrupt();
                } catch (Exception e) {
                    System.out.println(Constants._ERROR);
                }
            }
        }
    }
}
