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
                    try {
                        sleep(1000);
                        task.doTask(doing);
                        task.setStatus(false);
                    } catch (Exception e) {
                        System.out.println("Упс... Чтото пошло не так");
                    }
                }
            }
        }
    }
}
