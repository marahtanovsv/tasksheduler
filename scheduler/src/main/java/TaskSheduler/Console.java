package TaskSheduler;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Console {
    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        TaskDate model = new TaskDate();
        Parser parser = new Parser();
        String command = "";
        model.loadData();
        System.out.println(Constants._WERCOME);
        while (!command.equals("4")) {
            System.out.println(Constants._SELECTACTION);
            System.out.println(Constants._CREATETASK);
            System.out.println(Constants._VIEWTASKS);
            System.out.println(Constants._EDITTASK);
            System.out.println("4. "+Constants._EXIT);
            try {
                command = bf.readLine();
                switch (command) {
                    case ("1"):
                        createTask(bf, model);
                        break;
                    case ("2"):
                        model.viewTask();
                        break;
                    case ("3"):
                        try {
                            editTask(model);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(Constants._UNCORRECTDATE);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(Constants._TASKDONTEXIST);
                        } catch (NumberFormatException e) {
                            System.out.println(Constants._TASKNUMBERMUSTBENUMERIC);
                        }
                }

            } catch (IOException e) {
                System.out.println(Constants._UNCORRECTDATE);
            }
        }
        parser.writeObjectToXML(model);
        try {
            bf.close();
        } catch (IOException e) {

        }
    }

    public static void createTask(BufferedReader bf, TaskDate model) throws IOException {
        String createTaskCommand = "";
        Task task = model.createTask();
        if (task != null) {
            System.out.println(Constants._ENTERTASKNAME);
            task.setDescription(bf.readLine());
        } else
            while (model.existTask(task)) {
                System.out.println(Constants._ALREADYUSEDNAME);
                task.setDescription(bf.readLine());
            }
        while (!createTaskCommand.equals("3")) {
            System.out.println(Constants._SELECTACTION);
            System.out.println(Constants._SETDATA);
            System.out.println(Constants._SETACTION);
            System.out.println("3. "+Constants._EXIT);
            createTaskCommand = bf.readLine();
            switch (createTaskCommand) {
                case ("1"):
                    setDate(task);
                    break;
                case ("2"):
                    setAction(task);
                    break;
            }
        }
    }

    public static void setDate(Task task) throws IOException {
        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int day;
        int month;
        int year;
        int hour;
        int minute;
        int second;
        System.out.println(Constants._ENTERDAY);
        day = Integer.parseInt(bf.readLine());
        System.out.println(Constants._ENTERMONTH);
        month = Integer.parseInt(bf.readLine());
        System.out.println(Constants._ENTERYEAR);
        year = Integer.parseInt(bf.readLine());
        System.out.println(Constants._ENTERHOUR);
        hour = Integer.parseInt(bf.readLine());
        System.out.println(Constants._ENTERMINUTE);
        minute = Integer.parseInt(bf.readLine());
        System.out.println(Constants._ENTERSECOND);
        second = Integer.parseInt(bf.readLine());
        task.setData(new GregorianCalendar(year, month, day, hour, minute, second));
    }

    public static void setAction(Task task) throws IOException {
        String doString = "";
        while (!doString.equals("3")) {
            System.out.println(Constants._SELECTACTION);
            System.out.println(Constants._ENTERMESSAGE);
            System.out.println(Constants._ENTERPLAYSOUND);
            System.out.println("3. "+Constants._EXIT);
            doString = bf.readLine();
            switch (doString) {
                case ("1"):
                    task.setAction(Constants._PRINTMESSAGEACTION);
                    System.out.print(Constants._SETTASKMESSAGE);
                    task.setMessage(bf.readLine());
                    break;
                case ("2"):
                    task.setAction(Constants._PLAYSOUNDACTION);
                    break;
            }
        }
    }

    public static void editTask(TaskDate model) throws IOException {
        model.viewTask();
        Task task = null;
        String editTaskCommand = "";
        if (model.getTasks().isEmpty()) {
            System.out.println(Constants._TASKNOCHANGE);
        } else {
            System.out.println(Constants._ENTERTASKNUMBER);
            task = model.editTask(Integer.parseInt(bf.readLine()));
        }
        while (!editTaskCommand.equals("3")) {
            System.out.println(Constants._SETDATA);
            System.out.println(Constants._SETACTION);
            System.out.println("3. "+Constants._EXIT);
            editTaskCommand = bf.readLine();
            switch (editTaskCommand) {
                case ("1"):
                    editTaskDate(task);
                    break;
                case ("2"):
                    editActionTask(task);
                    break;
            }
        }
    }

    public static void editTaskDate(Task task) throws IOException {
        String editDateString = "";
        while (!editDateString.equals("7")) {
            System.out.println(Constants._SELECTACTION);
            System.out.println(Constants._EDITDAY);
            System.out.println(Constants._EDITMONTH);
            System.out.println(Constants._EDITYEAR);
            System.out.println(Constants._EDITHOUR);
            System.out.println(Constants._EDITMINNUTE);
            System.out.println(Constants._EDITSECOND);
            System.out.println("7. "+Constants._EXIT);

            try {
                editDateString = bf.readLine();
            } catch (IOException e1) {
                System.out.println(Constants._UNCORRECTDATE);
            }
            Calendar calendar = task.getData();
            switch (editDateString) {
                case ("1"):
                    setDataDayFromTask(calendar);
                    break;
                case ("2"):
                    setDataMonthFromTask(calendar);
                    break;
                case ("3"):
                    setDataYearFromTask(calendar);
                    break;
                case ("4"):
                    setDataHourFromTask(calendar);
                    break;
                case ("5"):
                    setDataMinuteFromTask(calendar);
                    break;
                case ("6"):
                    setDataSecondFromTask(calendar);
                    break;
            }

        }
    }

    public static void editActionTask(Task task) {
        String doString = "";
        while (!doString.equals("3")) {
            System.out.println(Constants._SELECTACTION);
            System.out.println(Constants._ENTERMESSAGE);
            System.out.println(Constants._ENTERPLAYSOUND);
            System.out.println("3. "+Constants._EXIT);
            try {
                doString = bf.readLine();
                switch (doString) {
                    case ("1"):
                        task.setAction(Constants._PRINTMESSAGEACTION);
                        System.out.print(Constants._SETTASKMESSAGE);
                        task.setMessage(bf.readLine());
                        break;
                    case ("2"):
                        task.setAction(Constants._PLAYSOUNDACTION);
                        break;
                }
            } catch (IOException e) {
                System.out.println(Constants._UNCORRECTDATE);
            }

        }
    }

    public static void setDataDayFromTask(Calendar calendar) throws IOException {
        System.out.println(Constants._ENTERDAY);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));

    }

    public static void setDataMonthFromTask(Calendar calendar) throws IOException {
        System.out.println(Constants._ENTERMONTH);
        calendar.set(Calendar.MONTH, Integer.parseInt(bf.readLine()));
    }

    public static void setDataYearFromTask(Calendar calendar) throws IOException {
        System.out.println(Constants._ENTERYEAR);
        calendar.set(Calendar.YEAR, Integer.parseInt(bf.readLine()));
    }

    public static void setDataHourFromTask(Calendar calendar) throws IOException {
        System.out.println(Constants._ENTERHOUR);
        calendar.set(Calendar.HOUR, Integer.parseInt(bf.readLine()));
    }

    public static void setDataMinuteFromTask(Calendar calendar) throws IOException {
        System.out.println(Constants._ENTERMINUTE);
        calendar.set(Calendar.MINUTE, Integer.parseInt(bf.readLine()));
    }

    public static void setDataSecondFromTask(Calendar calendar) throws IOException {
        System.out.println(Constants._ENTERSECOND);
        calendar.set(Calendar.SECOND, Integer.parseInt(bf.readLine()));
    }


}
