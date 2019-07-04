package TaskSheduler;

import java.util.GregorianCalendar;

public class Sheduler extends Thread  {

    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        while(true) {
            if (task.getData().equals(new GregorianCalendar().getTime())) {
                String doing = task.getDoing();
                if (!doing.isEmpty())task.doTask(doing);

            }
            try{
                wait(1000);
            }catch (Exception e){
                return;
            }
        }
    }
}
