package TaskSheduler;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

public class TaskDate {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void doTask(String message) {
        if (message.equals("message")) {
            System.out.println(message);
        }
        if (message.equals("bip")) {
            File clap = new File("beep.wav");
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(clap));
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void ViewTask() {
        int i = 0;
        for (Task task : tasks) {
            if (task.getDoing().equals("message"))
                System.out.println("Номер задачи " + i + "время запуска задачи: " + task.getData() + "Действие" + task.getDoing() + "Выводимое сообщение");
            else if (task.getDoing().equals("bip"))
                System.out.println("время запуска задачи: " + task.getData() + "Действие воспроизвести аудио файл");
        }
    }

    public void editTask(int numberTask) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Task task = tasks.get(numberTask);

        System.out.println("1. Задать время.");
        System.out.println("2. Задать действие");
        System.out.println("3. Выход");
        String editTaskCommand = null;
        try {
            while (true) {
                editTaskCommand = bf.readLine();
                switch (editTaskCommand) {
                    case ("1"):
                        while (true) {
                            System.out.println("что необходимо изменить.");
                            System.out.println("1.день");
                            System.out.println("2.месяц");
                            System.out.println("3.год");
                            System.out.println("4.час");
                            System.out.println("5.минуту");
                            System.out.println("6.секунду");
                            System.out.println("7.Отмена");
                            String editDateString = null;
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
                                case ("2"):
                                    System.out.println("Введите месяц");
                                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                case ("3"):
                                    System.out.println("Введите год");
                                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                case ("4"):
                                    System.out.println("Введите час");
                                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                case ("5"):
                                    System.out.println("Введите минуту");
                                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                case ("6"):
                                    System.out.println("Введите секунду");
                                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(bf.readLine()));
                                case ("7"):
                                    break;
                            }

                        }

                    case ("2"):
                        while (true) {
                            System.out.println("выберите действие");
                            System.out.println("1. Вывести сообщение.");
                            System.out.println("2. Воспроизвести аудио файл");
                            System.out.println("3. Выход");
                            try {
                                String doString = bf.readLine();
                                switch (doString) {
                                    case ("1"):
                                        task.setDoing("Message");
                                        System.out.print("Введите сообщение: ");
                                        task.setMessage(bf.readLine());
                                    case ("2"):
                                        task.setDoing("bip");
                                    case ("3"):
                                        break;
                                }
                            } catch (IOException e) {
                                System.out.println("Введены некоректные данные, необходимо ввести номер меню");
                            }

                        }
                    case ("3"):
                        break;
                }
            }
        } catch (IOException e)

        {
            System.out.println("Введены некоректные данные, необходимо ввести номер меню");
        }

    }
}

