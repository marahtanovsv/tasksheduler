package TaskSheduler;

import java.sql.*;

public class JDBS {
    private static final String URL="jdbs:mysql://localhost:5432/taskSheduler";
    private static final String USER="postgres";
    private static final String P2SSWORD="P@ssw0rd";

    public void writeJDBS(TaskDate tasks) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        //Class.forName("org.postgresql.Driver");
        System.out.println("Драйвер подключен");
        //Создаём соединение
        connection = DriverManager.getConnection(URL, USER, P2SSWORD);
        System.out.println("Соединение установлено");
        //Для использования SQL запросов существуют 3 типа объектов:
        //1.Statement: используется для простых случаев без параметров
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO tasks(description,action,status) values('123','Message',true)");
        ResultSet result1 = statement.executeQuery(
                "SELECT * FROM tasks");
        //result это указатель на первую строку с выборки
        //чтобы вывести данные мы будем использовать
        //метод next() , с помощью которого переходим к следующему элементу
        System.out.println("Выводим statement");
        while (result1.next()) {
            System.out.println("Номер в выборке #" + result1.getRow()
                    + "\t Номер в базе #" + result1.getInt("id")
                    + "\t" + result1.getString("username"));
        }

    }

    public TaskDate readJDBS(){

    return null;
    }
}
