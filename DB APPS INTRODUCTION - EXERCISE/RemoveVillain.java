import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveVillain {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions?useSSL=false",
                "root", "34273427")) {

            int villainId = Integer.parseInt(input.nextLine());

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM minions.minions" +
                    " as m JOIN minions.minions_villains as mv" +
                    " ON mv.minion_id = m.id WHERE  mv.villain_id = ?");

            statement.setInt(1, villainId);

            List<Integer> minionsToDelete = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("name"));
                minionsToDelete.add(resultSet.getInt("id"));
            }

            removeFromMinionsVillains(connection, minionsToDelete);
            String villainName = getVillain(connection, villainId);
            removeFromMinionsTable(connection, minionsToDelete);
            removeVillain(connection, villainId);

            System.out.println(villainName + " was deleted");
            System.out.println(String.format("%d minions released", minionsToDelete.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getVillain(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from minions.villains;");
        ResultSet villains = statement.executeQuery();

        while (villains.next()) {
            if (villainId == villains.getInt("id")) {
                return villains.getString("name");
            }
        }

        return "";
    }

    private static void removeVillain(Connection connection, int villainId) throws SQLException {
        String sql = "delete from villains where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, villainId);
        statement.executeUpdate();
    }

    private static void removeFromMinionsTable(Connection connection, List<Integer> minionsIds) throws SQLException {
        String sql = "delete from minions where id = ?;";

        PreparedStatement statement = connection.prepareStatement(sql);

        for (int minionId : minionsIds) {
            statement.setInt(1, minionId);
            statement.executeUpdate();
        }

        statement.close();
    }

    private static void removeFromMinionsVillains(Connection connection, List<Integer> minionsIds) throws SQLException {
        String sql = "delete from minions_villains where minion_id = ?;";

        PreparedStatement statement = connection.prepareStatement(sql);

        for (int minionId : minionsIds) {
            statement.setInt(1, minionId);
            statement.executeUpdate();
        }

        statement.close();
    }

}
