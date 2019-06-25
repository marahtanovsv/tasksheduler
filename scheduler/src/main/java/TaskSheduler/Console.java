package TaskSheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                        model.addTaskToTasks(task);
                        break;
                    case ("2"):
                        model.ViewTask();
                        break;
                    case ("3"):
                        while (true) {
                            try {
                                System.out.println("Введите номер задачи для редактирования");
                                try {
                                    model.editTask(Integer.parseInt(bf.readLine()) - 1);
                                    break;
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Введены не коректные данные");
                                }catch (IndexOutOfBoundsException e){
                                    System.out.println("Данной задачи не существует");
                                }catch (NumberFormatException e){
                                    System.out.println("Номер задачи должен быть числом");
                                }

                            } catch (IOException e) {
                                System.out.println("Введены некоретные данные");
                            }
                        }

                }

            } catch (IOException e) {
                System.out.println("Введен недопустимый параметр.");
            }

        }
    }

}
