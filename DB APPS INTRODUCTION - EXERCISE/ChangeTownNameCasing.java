import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNameCasing {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions?useSSL=false",
                "root", "34273427")) {

            String countryName = input.nextLine();

            updateTowns(countryName, connection);

            selectTowns(countryName, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectTowns(String countryName, Connection connection) {
        String sql = "SELECT \n" +
                "    *, c.name\n" +
                "FROM\n" +
                "    towns AS t\n" +
                "        JOIN\n" +
                "    countries AS c ON c.id = t.country_id\n" +
                "    where c.name = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, countryName);

            ResultSet resultSet = statement.executeQuery();
            List<String> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(resultSet.getString("t.name"));
            }

            if (results.size() > 0) {
                System.out.println(results.size() + " town names were affected.");
                System.out.println(results);
            } else {
                System.out.println("No town names were affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateTowns(String countryName, Connection connection) {
        String sql = "UPDATE towns AS t\n" +
                "        JOIN\n" +
                "    countries AS c ON c.id = t.country_id \n" +
                "SET \n" +
                "    t.name = UPPER (t.name)\n" +
                "WHERE\n" +
                "    c.name = ?;";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, countryName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
