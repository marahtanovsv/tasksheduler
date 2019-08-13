package TaskSheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Console {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TaskDate model = new TaskDate();
        Parser parser = new Parser();
        String command = "";
        JDBS jdbs = new JDBS();
        jdbs.writeJDBS();
        model.loadData();
        System.out.println(Constants.WELCOME);
        while (!command.equals("4")) {
            System.out.println(Constants.SELECT_ACTION);
            System.out.println(Constants.CREATE_TASK);
            System.out.println(Constants.VIEW_TASKS);
            System.out.println(Constants.EDIT_TASK);
            System.out.println("4. " + Constants.EXIT);
            try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))){
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
                            editTask(model,bf);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(Constants.UNCORRECT_DATE);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(Constants.TASK_DONT_EXIST);
                        } catch (NumberFormatException e) {
                            System.out.println(Constants.TASK_NUMBER_MUST_BE_NUMERIC);
                        }
                }

            } catch (IOException e) {
                System.out.println(Constants.UNCORRECT_DATE);
            }
        }
        parser.writeObjectToXML(model);
    }

    public static void createTask(BufferedReader bf, TaskDate model) throws IOException {
        String createTaskCommand = "";
        Task task = model.createTask();
        if (task != null) {
            System.out.println(Constants.ENTER_TASK_NAME);
            task.setDescription(bf.readLine());
        } else
            while (model.existTask(task)) {
                System.out.println(Constants.ALREADY_USED_NAME);
                task.setDescription(bf.readLine());
            }
        while (!createTaskCommand.equals("3")) {
            System.out.println(Constants.SELECT_ACTION);
            System.out.println(Constants.SET_DATA);
            System.out.println(Constants.SET_ACTION);
            System.out.println("3. "+Constants.EXIT);
            createTaskCommand = bf.readLine();
            switch (createTaskCommand) {
                case ("1"):
                    setDate(task,bf);
                    break;
                case ("2"):
                    setAction(task,bf);
                    break;
            }
        }
    }

    public static void setDate(Task task, BufferedReader bf) throws IOException {
        GregorianCalendar gc = new GregorianCalendar();
        int day;
        int month;
        int year;
        int hour;
        int minute;
        int second;
        System.out.println(Constants.ENTER_DAY);
        day = Integer.parseInt(bf.readLine());
        System.out.println(Constants.ENTER_MONTH);
        month = Integer.parseInt(bf.readLine());
        System.out.println(Constants.ENTER_YEAR);
        year = Integer.parseInt(bf.readLine());
        System.out.println(Constants.ENTER_HOUR);
        hour = Integer.parseInt(bf.readLine());
        System.out.println(Constants.ENTER_MINUTE);
        minute = Integer.parseInt(bf.readLine());
        System.out.println(Constants.ENTER_SECOND);
        second = Integer.parseInt(bf.readLine());
        task.setData(new GregorianCalendar(year, month, day, hour, minute, second));
    }

    public static void setAction(Task task, BufferedReader bf) throws IOException {
        String doString = "";
        while (!doString.equals("3")) {
            System.out.println(Constants.SELECT_ACTION);
            System.out.println(Constants.ENTER_MESSAGE);
            System.out.println(Constants.ENTER_PLAY_SOUND);
            System.out.println("3. "+Constants.EXIT);
            doString = bf.readLine();
            switch (doString) {
                case ("1"):
                    task.setAction(Constants.PRINT_MESSAGE_ACTION);
                    System.out.print(Constants.SET_TASK_MESSAGE);
                    task.setMessage(bf.readLine());
                    break;
                case ("2"):
                    task.setAction(Constants.PLAY_SOUND_ACTION);
                    break;
            }
        }
    }

    public static void editTask(TaskDate model, BufferedReader bf) throws IOException {
        model.viewTask();
        Task task = null;
        String editTaskCommand = "";
        if (model.getTasks().isEmpty()) {
            System.out.println(Constants.TASK_NO_CHANGE);
        } else {
            System.out.println(Constants.ENTER_TASK_NUMBER);
            task = model.editTask(Integer.parseInt(bf.readLine()));
        }
        while (!editTaskCommand.equals("3")) {
            System.out.println(Constants.SET_DATA);
            System.out.println(Constants.SET_ACTION);
            System.out.println("3. "+Constants.EXIT);
            editTaskCommand = bf.readLine();
            switch (editTaskCommand) {
                case ("1"):
                    editTaskDate(task,bf);
                    break;
                case ("2"):
                    editActionTask(task,bf);
                    break;
            }
        }
    }

    public static void editTaskDate(Task task, BufferedReader bf) throws IOException {
        String editDateString = "";
        while (!editDateString.equals("7")) {
            System.out.println(Constants.SELECT_ACTION);
            System.out.println(Constants.EDIT_DAY);
            System.out.println(Constants.EDIT_MONTH);
            System.out.println(Constants.EDIT_YEAR);
            System.out.println(Constants.EDIT_HOUR);
            System.out.println(Constants.EDIT_MINNUTE);
            System.out.println(Constants.EDIT_SECOND);
            System.out.println("7. "+Constants.EXIT);

            try {
                editDateString = bf.readLine();
            } catch (IOException e1) {
                System.out.println(Constants.UNCORRECT_DATE);
            }
            GregorianCalendar calendar = task.getData();
            switch (editDateString) {
                case ("1"):
                    calendar=setDataDayFromTask(calendar,bf);
                    break;
                case ("2"):
                    calendar=setDataMonthFromTask(calendar,bf);
                    break;
                case ("3"):
                    calendar=setDataYearFromTask(calendar,bf);
                    break;
                case ("4"):
                    calendar=setDataHourFromTask(calendar,bf);
                    break;
                case ("5"):
                    calendar=setDataMinuteFromTask(calendar,bf);
                    break;
                case ("6"):
                    calendar=setDataSecondFromTask(calendar,bf);
                    break;
            }
            task.setData(calendar);
            if(new GregorianCalendar().getTime().before(task.getData().getTime())){
                task.setStatus(true);
                //task.startSheduler();
            }

        }
    }

    public static void editActionTask(Task task, BufferedReader bf) {
        String doString = "";
        while (!doString.equals("3")) {
            System.out.println(Constants.SELECT_ACTION);
            System.out.println(Constants.ENTER_MESSAGE);
            System.out.println(Constants.ENTER_PLAY_SOUND);
            System.out.println("3. "+Constants.EXIT);
            try {
                doString = bf.readLine();
                switch (doString) {
                    case ("1"):
                        task.setAction(Constants.PRINT_MESSAGE_ACTION);
                        System.out.print(Constants.SET_TASK_MESSAGE);
                        task.setMessage(bf.readLine());
                        break;
                    case ("2"):
                        task.setAction(Constants.PLAY_SOUND_ACTION);
                        break;
                }
            } catch (IOException e) {
                System.out.println(Constants.UNCORRECT_DATE);
            }

        }
    }

    public static GregorianCalendar setDataDayFromTask(GregorianCalendar calendar, BufferedReader bf) throws IOException {
        System.out.println(Constants.ENTER_DAY);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
        return calendar;
    }

    public static GregorianCalendar setDataMonthFromTask(GregorianCalendar calendar, BufferedReader bf) throws IOException {
        System.out.println(Constants.ENTER_MONTH);
        calendar.set(Calendar.MONTH, Integer.parseInt(bf.readLine()));
        return calendar;
    }

    public static GregorianCalendar setDataYearFromTask(GregorianCalendar calendar, BufferedReader bf) throws IOException {
        System.out.println(Constants.ENTER_YEAR);
        calendar.set(Calendar.YEAR, Integer.parseInt(bf.readLine()));
        return calendar;
    }

    public static GregorianCalendar setDataHourFromTask(GregorianCalendar calendar, BufferedReader bf) throws IOException {
        System.out.println(Constants.ENTER_HOUR);
        calendar.set(Calendar.HOUR, Integer.parseInt(bf.readLine()));
        return calendar;
    }

    public static GregorianCalendar setDataMinuteFromTask(GregorianCalendar calendar, BufferedReader bf) throws IOException {
        System.out.println(Constants.ENTER_MINUTE);
        calendar.set(Calendar.MINUTE, Integer.parseInt(bf.readLine()));
        return calendar;
    }

    public static GregorianCalendar setDataSecondFromTask(GregorianCalendar calendar, BufferedReader bf) throws IOException {
        System.out.println(Constants.ENTER_SECOND);
        calendar.set(Calendar.SECOND, Integer.parseInt(bf.readLine()));
        return calendar;
    }


}
