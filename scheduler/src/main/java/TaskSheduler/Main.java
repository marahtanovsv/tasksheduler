package TaskSheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.GregorianCalendar;

public class Main {
    public static void main(String []args) {
        TaskDate model = new TaskDate();

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String command="";
        while(true) {
            System.out.println("Добро пожаловать в планировшик задач");
            System.out.println("1. Создать задачу.");
            System.out.println("2. Вывести список задач.");
            System.out.println("3. Изменить задачу.");
            System.out.println("4. Выход");
            try {
                command = bf.readLine();
                switch (command){
                    case ("1"):
                        String createTaskCommand;
                        Task task = new Task();
                        while (true){
                            createTaskCommand= bf.readLine();
                        System.out.println("Задача успешно создана");
                        System.out.println("1. Задать время.");
                        System.out.println("2. Задать действие");
                        System.out.println("3. Выход");
                            switch (createTaskCommand){
                                case ("1"):
                                    int day;
                                    int month;
                                    int year;
                                    int hour;
                                    int minute;
                                    int second;
                                    System.out.println("введите день(число)");
                                    day=Integer.parseInt(bf.readLine());
                                    System.out.println("введите месяц(число)");
                                    month=Integer.parseInt(bf.readLine());
                                    System.out.println("введите год(число)");
                                    year=Integer.parseInt(bf.readLine());
                                    System.out.println("введите час(число)");
                                    hour=Integer.parseInt(bf.readLine());
                                    System.out.println("введите минуту(число)");
                                    minute=Integer.parseInt(bf.readLine());
                                    System.out.println("введите секунду(число)");
                                    second=Integer.parseInt(bf.readLine());
                                    task.setData(new GregorianCalendar(year,month,day,hour,minute,second));
                                case ("2"):
                                    while (true){
                                        System.out.println("выберите действие");
                                        System.out.println("1. Вывести сообщение.");
                                        System.out.println("2. Воспроизвести аудио файл");
                                        System.out.println("3. Выход");
                                        String doString=bf.readLine();
                                        switch (doString){
                                            case ("1"):
                                                task.setDoing("Message");
                                                System.out.print("Введите сообщение: ");
                                                task.setMessage(bf.readLine());
                                            case ("2"):
                                                task.setDoing("bip");
                                            case ("3"):
                                                break;
                                        }
                                    }
                                case ("3"):break;
                            }

                        }
                    case ("2"):
                        model.ViewTask();
                    case ("3"):
                        try{
                            System.out.println("Введите номер задачи для редактирования");
                            model.editTask(Integer.parseInt(bf.readLine()));
                        }catch (IOException e){
                            System.out.println("Введены некоретные данные");
                        }
                    case ("4"): break;
                }

            }
            catch (IOException e){
                System.out.println("Введен недопустимый параметр.");
            }

        }
    }

}
