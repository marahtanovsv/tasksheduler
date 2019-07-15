package TaskSheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Console {
    public static void main(String[] args) {
        TaskDate model = new TaskDate();
        Parser parser = new Parser();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        model.loadData();
        System.out.println("Добро пожаловать в планировшик задач");
        while (!command.equals("4")) {

            System.out.println("1. Создать задачу.");
            System.out.println("2. Вывести список задач.");
            System.out.println("3. Изменить задачу.");
            System.out.println("4. Выход");
            try {
                command = bf.readLine();
                switch (command) {
                    case ("1"):
                        String createTaskCommand = "";
                        Task task = model.createTask();
                        if (task != null) {
                            System.out.println("Введите имя задачи");
                            task.setDescription(bf.readLine());
                        } else
                            while (model.existTask(task)) {
                                System.out.println("Вы ошиблись вводе или такое имя уже используется. Повторите ввод.");
                                task.setDescription(bf.readLine());
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
                                                //editTaskFlag = true;
                                                break;
                                            case ("2"):
                                                task.setDoing("bip");
                                                //editTaskFlag = true;
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        break;
                    case ("2"):
                        model.viewTask();
                        break;
                    case ("3"):
                        while (true) {
                            try {
                                model.viewTask();
                                //boolean startTaskDoEdit = true;
                                String editTaskCommand = "";
                                if (model.getTasks().isEmpty()) {
                                    System.out.println("Нет задач для изменения.");
                                    break;
                                } else {
                                    System.out.println("Введите номер задачи для редактирования");
                                    task = model.editTask(Integer.parseInt(bf.readLine()));
                                }
                                while (!editTaskCommand.equals("3")) {
                                    System.out.println("1. Задать время.");
                                    System.out.println("2. Задать действие");
                                    System.out.println("3. Выход");
                                    editTaskCommand = bf.readLine();

                                    String editDateString = "";
                                    switch (editTaskCommand) {
                                        case ("1"):
                                            while (!editDateString.equals("7")) {
                                                System.out.println("что необходимо изменить.");
                                                System.out.println("1. День");
                                                System.out.println("2. Месяц");
                                                System.out.println("3. Год");
                                                System.out.println("4. Час");
                                                System.out.println("5. Минуту");
                                                System.out.println("6. Секунду");
                                                System.out.println("7. Отмена");

                                                try {
                                                    editDateString = bf.readLine();
                                                } catch (IOException e1) {
                                                    System.out.println("Введены некоректные данные, необходимо ввести номер меню(от 1 до 7)");
                                                }
                                                Calendar calendar = task.getData();
                                                switch (editDateString) {
                                                    case ("1"):
                                                        System.out.println("Введите день");
                                                        int day = Integer.parseInt(bf.readLine());
                                                        calendar.set(Calendar.DAY_OF_MONTH, day);
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
                                                }

                                            }
                                            break;
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
                                            break;
                                    }
                                }

                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Введены не коректные данные");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Данной задачи не существует");
                            } catch (NumberFormatException e) {
                                System.out.println("Номер задачи должен быть числом");
                            }
                            break;
                        }

                }

            } catch (IOException e) {
                System.out.println("Введен недопустимый параметр.");
            }
        }
        parser.writeObjectToXML(model);
    }

}
