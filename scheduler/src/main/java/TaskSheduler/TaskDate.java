package TaskSheduler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@XmlRootElement
public class TaskDate {
    private long idTask;
    private Parser parser = new Parser();
    private ArrayList<Task> tasks = new ArrayList<>();

    @XmlElement
    public void setIdTask(long idTask) {
        this.idTask = idTask;
    }

    @XmlElement
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @XmlElement
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public long getIdTask() {
        return idTask;
    }

    public Parser getParser() {
        return parser;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void viewTask() {
        if (tasks.isEmpty()) System.out.println(Constants._DONTCREATEDTASK);
        else {
            for (Task taskList : tasks) {
                System.out.println(taskList.toString());
            }
        }
    }

    public void loadData() {
        this.tasks = parser.readXMLToObject().getTasks();
        Comparator<Task> comparator = Comparator.comparing(obj -> obj.getId());
        Collections.sort(tasks, comparator);
        if (tasks.isEmpty()) idTask = 0;
        else {
            idTask = tasks.size();
        }
        for(Task listTask:tasks){
            if(listTask.getStatus()) listTask.startSheduler();
        }

    }

    public boolean addTaskToTasks(Task task) {
        if (existTask(task)) return false;
        else {

            return tasks.add(task);
        }
    }

    public Task editTask(int numberTask) {
        return tasks.get(numberTask);
    }

    public boolean existTask(Task task) {
        boolean exist = false;
        for (Task tasklist : tasks) {
            if (task.getId() == tasklist.getId()) exist = true;
        }
        return exist;
    }

    public Task createTask() {
        Task task = new Task(idTask);
        idTask++;
        boolean areAddedTasks = addTaskToTasks(task);
        if (areAddedTasks) {
            System.out.println(Constants._TASKDONTCREATE);
            task.startSheduler();
            return task;
        } else return null;
    }
}

