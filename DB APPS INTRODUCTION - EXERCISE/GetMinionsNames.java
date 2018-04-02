import java.sql.*;
import java.util.Scanner;

public class GetMinionsNames {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String user = "root";
        String password = "34273427";

        String sql = "SELECT \n" +
                "    v.id,\n" +
                "    v.name,\n" +
                "    v.evilness_factor,\n" +
                "    m.id,\n" +
                "    m.name,\n" +
                "    m.age,\n" +
                "    m.town_id\n" +
                "FROM\n" +
                "    villains AS v\n" +
                "        LEFT JOIN\n" +
                "    minions_villains AS mv ON mv.villain_id = v.id\n" +
                "        LEFT JOIN\n" +
                "    minions AS m ON mv.minion_id = m.id\n" +
                "WHERE\n" +
                "    v.id = ?;";


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions?useSSL=false",
                user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            System.out.print("Enter villain's id: ");
            int id = Integer.parseInt(input.nextLine());
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                if (count == 0) {
                    System.out.println("Villain: " + resultSet.getString("name"));
                    count++;
                }
                String nameOfMinion = resultSet.getString("m.name");
                String ageOfMinion = resultSet.getString("m.age");
                if (nameOfMinion == null || ageOfMinion == null) {
                    continue;
                }
                System.out.println(String.format("%d. %s %s", count++, nameOfMinion, ageOfMinion));
            }
            if (count == 0) {
                System.out.println(String.format("No villain with ID %d exists in the database.", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
