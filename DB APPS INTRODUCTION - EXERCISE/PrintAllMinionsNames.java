import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionsNames {

    public static void main(String[] args) {
        String sql = "select * from minions;";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions?useSSL=false",
                "root", "34273427");
             PreparedStatement statement = connection.prepareStatement(sql)) {

            List<String> minions = new ArrayList<>();
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                minions.add(set.getString("name"));
            }

            List<Integer> sequence = new ArrayList<>();

            for (int i = 0; i < minions.size(); i += 2) {
                sequence.add(i + 1);
                System.out.println(minions.get(i));
            }

            for (int i = minions.size() - 1; i >= 0; i -= 2) {
                sequence.add(i + 1);
                System.out.println(minions.get(i));
            }

            System.out.println(sequence);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
