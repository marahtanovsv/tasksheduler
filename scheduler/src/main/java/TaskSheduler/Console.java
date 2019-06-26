package TaskSheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Console {
    public static void main(String[] args) {
        TaskDate model = new TaskDate();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        boolean createTaskDataFlag = false;
        boolean createTaskDoFlag = false;
        boolean editTaskFlag = false;
        while (!command.equals("4")) {
            System.out.println("Добро пожаловать в планировшик задач");
            System.out.println("1. Создать задачу.");
            System.out.println("2. Вывести список задач.");
            System.out.println("3. Изменить задачу.");
            System.out.println("4. Выход");
            try {
                command = bf.readLine();
                switch (command) {
                    case ("1"):
                        String createTaskCommand = "";
                        Task task = new Task();
                        String nameTask = "";
                        while (nameTask.isEmpty()) {
                            System.out.println("Введите имя задачи");
                            nameTask = bf.readLine();
                            task.setDescription(nameTask);

                        }
                        while (!createTaskCommand.equals("3")) {
                            System.out.println("выберите действие");
                            System.out.println("1. Задать время.");
                            System.out.println("2. Задать действие");
                            System.out.println("3. Выход");
                            createTaskCommand = bf.readLine();
                            switch (createTaskCommand) {
                                case ("1"):
                                    int day;
                                    int month;
                                    int year;
                                    int hour;
                                    int minute;
                                    int second;
                                    System.out.println("введите день(число)");
                                    day = Integer.parseInt(bf.readLine());
                                    System.out.println("введите месяц(число)");
                                    month = Integer.parseInt(bf.readLine());
                                    System.out.println("введите год(число)");
                                    year = Integer.parseInt(bf.readLine());
                                    System.out.println("введите час(число)");
                                    hour = Integer.parseInt(bf.readLine());
                                    System.out.println("введите минуту(число)");
                                    minute = Integer.parseInt(bf.readLine());
                                    System.out.println("введите секунду(число)");
                                    second = Integer.parseInt(bf.readLine());
                                    task.setData(new GregorianCalendar(year, month, day, hour, minute, second));
                                    createTaskDataFlag = true;
                                    break;
                                case ("2"):
                                    String doString = "";
                                    while (!doString.equals("3")) {
                                        System.out.println("выберите действие");
                                        System.out.println("1. Вывести сообщение.");
                                        System.out.println("2. Воспроизвести аудио файл");
                                        System.out.println("3. Выход");
                                        doString = bf.readLine();
                                        switch (doString) {
                                            case ("1"):
                                                task.setDoing("Message");
                                                System.out.print("Введите сообщение: ");
                                                task.setMessage(bf.readLine());
                                                editTaskFlag = true;
                                                break;
                                            case ("2"):
                                                task.setDoing("bip");
                                                editTaskFlag = true;
                                                break;
                                        }

                                    }
                            }
                        }
                        if (model.addTaskToTasks(task)) System.out.println("Задача успешно создана");
                        break;
                    case ("2"):
                        printTaskList(model);
                        break;
                    case ("3"):
                        while (true) {
                            try {
                                printTaskList(model);
                                boolean startTaskDoEdit=true;
                                String editTaskCommand = "";
                                while (!editTaskCommand.equals("3")) {
                                    if(startTaskDoEdit) {
                                        System.out.println("Введите номер задачи для редактирования");
                                        task = model.editTask(Integer.parseInt(bf.readLine()) - 1);
                                    }
                                    else task=null;
                                    System.out.println("1. Задать время.");
                                    System.out.println("2. Задать действие");
                                    System.out.println("3. Выход");
                                    editTaskCommand = bf.readLine();

                                    String editDateString = "";
                                    switch (editTaskCommand) {
                                        case ("1"):
                                            while (!editDateString.equals("7")) {
                                                System.out.println("что необходимо изменить.");
                                                System.out.println("1.день");
                                                System.out.println("2.месяц");
                                                System.out.println("3.год");
                                                System.out.println("4.час");
                                                System.out.println("5.минуту");
                                                System.out.println("6.секунду");
                                                System.out.println("7.Отмена");

                                                try {
                                                    editDateString = bf.readLine();
                                                } catch (IOException e1) {
                                                    System.out.println("Введены некоректные данные, необходимо ввести номер меню(от 1 до 7)");
                                                }
                                                Calendar calendar = task.getData();
                                                switch (editDateString) {
                                                    case ("1"):
                                                        System.out.println("Введите день");
                                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                                        startTaskDoEdit=false;
                                                        break;
                                                    case ("2"):
                                                        System.out.println("Введите месяц");
                                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                                        break;
                                                    case ("3"):
                                                        System.out.println("Введите год");
                                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                                        break;
                                                    case ("4"):
                                                        System.out.println("Введите час");
                                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                                        break;
                                                    case ("5"):
                                                        System.out.println("Введите минуту");
                                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                                        break;
                                                    case ("6"):
                                                        System.out.println("Введите секунду");
                                                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                                        break;
                                                    case ("7"):
                                                        break;
                                                }
                                            }

                                        case ("2"):
                                            String doString = "";
                                            while (!doString.equals("3")) {
                                                System.out.println("выберите действие");
                                                System.out.println("1. Вывести сообщение.");
                                                System.out.println("2. Воспроизвести аудио файл");
                                                System.out.println("3. Выход");
                                                try {
                                                    doString = bf.readLine();
                                                    switch (doString) {
                                                        case ("1"):
                                                            task.setDoing("Message");
                                                            System.out.print("Введите сообщение: ");
                                                            task.setMessage(bf.readLine());
                                                            break;
                                                        case ("2"):
                                                            task.setDoing("bip");
                                                            break;
                                                    }
                                                } catch (IOException e) {
                                                    System.out.println("Введены некоректные данные, необходимо ввести номер меню");
                                                }

                                            }
                                    }
                                }


                                break;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Введены не коректные данные");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Данной задачи не существует");
                            } catch (NumberFormatException e) {
                                System.out.println("Номер задачи должен быть числом");
                            }

                        }

                }

            } catch (IOException e) {
                System.out.println("Введен недопустимый параметр.");
            }

        }
    }
    public static void printTaskList(TaskDate model){
        int i = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy  HH:mm:ss ");
        for (Task taskList : model.viewTask()) {
            try {
                if (taskList.getDoing().equals("Message"))
                    System.out.println(i+") Имя задачи " + taskList.getDescription() + " время запуска задачи: " + dateFormat.format(taskList.getData().getTime()) + " Действие " + taskList.getDoing() + " Выводимое сообщение " + taskList.getMessage());
                else if (taskList.getDoing().equals("bip"))
                    System.out.println(i+") Имя задачи " + taskList.getDescription() + " время запуска задачи: " + dateFormat.format(taskList.getData().getTime()) + "Действие воспроизвести аудио файл");
            } catch (NullPointerException e) {
                System.out.println(i+") Имя задачи " + taskList.getDescription() + " время запуска задачи: " + dateFormat.format(taskList.getData().getTime()) + " Действие не задано");
            }
            i++;
        }
    }

}
