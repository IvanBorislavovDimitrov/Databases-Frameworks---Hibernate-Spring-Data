import java.sql.*;
import java.util.*;

public class IncreaseMinionsAge {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions?useSSL=false",
                "root", "34273427")) {
            int[] ids = Arrays.stream(input.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            for (int id : ids) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM minions.minions");
                Map<Integer, String> minions = new HashMap<>();

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    minions.put(resultSet.getInt("id"), resultSet.getString("name"));
                }

                for (Map.Entry<Integer, String> minion : minions.entrySet()) {
                    String update = "update minions set name = ?, age = age + 1 WHERE id = ?;";
                    String minionName = minion.getValue();
                    minionName = changeName(minionName);

                    statement.close();

                    statement = connection.prepareStatement(update);

                    statement.setString(1, minionName);
                    statement.setInt(2, id);
                    statement.executeUpdate();

                    statement.close();
                }
            }

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM minions.minions");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(String.format("%s %s %s", resultSet.getString("id"), resultSet.getString("name"),
                        resultSet.getString("age")));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String changeName(String name) {
        String[] names = name.split("\\s+");
        int count = 0;
        for (String currName : names) {
            if (Character.isUpperCase(currName.charAt(0))) {
                names[count] = currName.substring(0, 1).toUpperCase() + currName.substring(1);
            }
            count++;
        }

        return String.join(" ", Arrays.asList(names));
    }
}
