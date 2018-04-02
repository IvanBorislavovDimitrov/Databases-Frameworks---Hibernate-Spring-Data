import java.sql.*;
import java.util.Scanner;

public class Min {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String sql = "\n" +
                "create procedure udp_get_older(minion_id int)\n" +
                "begin\n" +
                "\tupdate minions set age = age + 1 where id = minion_id;\n" +
                "end ;\n" +
                "";

        String call = "call udp_get_older(?);";
        String getAll = "select * from minions where id = ?;";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions?useSSL=false",
                "root", "34273427");
             CallableStatement stmt = connection.prepareCall(call);
             PreparedStatement preparedStatement = connection.prepareStatement(getAll);
             PreparedStatement preparedStatement1 = connection.prepareStatement(sql)) {

            try {
                preparedStatement1.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Procedure is created!");

            System.out.print("Enter minion's id: ");
            int minionId = Integer.parseInt(input.nextLine());
            stmt.setInt(1, minionId);

            stmt.executeUpdate();

            preparedStatement.setInt(1, minionId);

            ResultSet minions = preparedStatement.executeQuery();

            while (minions.next()) {
                System.out.println(String.format("%s %s %s %s", minions.getString("id"),
                        minions.getString("name"),
                        minions.getString("age"),
                        minions.getString("town_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}