package TaskSheduler;



import java.sql.*;

public class JDBS {
    private static final String URL="jdbc:postgresql://localhost:5432/Tasks";
    private static final String USER="postgres";
    private static final String P2SSWORD="P@ssw0rd";

    public void writeJDBS() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        Class.forName("org.postgresql.Driver");
        System.out.println("Драйвер подключен");
        connection = DriverManager.getConnection(URL, USER, P2SSWORD);
        System.out.println("Соединение установлено");

        statement = connection.createStatement();
        //statement.executeUpdate("INSERT INTO public.\"Tasks\"(action,description,status) values('Message','323',true)");
        ResultSet result1 = statement.executeQuery(
                "SELECT * FROM public.\"Tasks\"");
               System.out.println("Выводим statement");
        while (result1.next()) {
            System.out.println(result1.getString("description")
                    + "\t" + result1.getString("action")
                    + "\t" + result1.getBoolean("status"));
        }

    }

    public TaskDate readJDBS(){

    return null;
    }
}
