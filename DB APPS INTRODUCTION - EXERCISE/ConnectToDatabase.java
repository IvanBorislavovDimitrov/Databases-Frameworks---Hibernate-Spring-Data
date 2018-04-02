import java.sql.*;
import java.util.Scanner;

public class ConnectToDatabase {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String user = "root";
        String password = "34273427";

        String sql = "SELECT \n" +
                "    *\n" +
                "FROM\n" +
                "    minions AS m\n" +
                "        JOIN\n" +
                "    minions_villains AS mv ON mv.minion_id = m.id\n" +
                "        JOIN\n" +
                "    towns AS t ON t.id = m.town_id\n" +
                "        JOIN\n" +
                "    countries AS c ON c.id = t.country_id\n" +
                "        JOIN\n" +
                "    villains AS v ON v.id = mv.villain_id;";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions?useSSL=false",
                user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(String.format("%s %s %s %s", resultSet.getString("id"),
                        resultSet.getString("age"),
                        resultSet.getString("name"),
                        resultSet.getString("town_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
