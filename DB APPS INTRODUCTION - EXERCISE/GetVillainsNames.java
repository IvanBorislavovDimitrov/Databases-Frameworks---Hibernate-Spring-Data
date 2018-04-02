import java.sql.*;

public class GetVillainsNames {

    private static final String USER = "root";

    private static final String PASSWORD = "34273427";

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = createConnectionToDatabase("minions");

            ResultSet villains = getVillains(connection);

            printResults(villains);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printResults(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println(String.format("%s - %s", resultSet.getString("name"),
                    resultSet.getString("count_of_minions")));
        }
    }

    private static ResultSet getVillains(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT \n" +
                "    v.name, COUNT(m.id) AS `count_of_minions`\n" +
                "FROM\n" +
                "    villains AS v\n" +
                "        JOIN\n" +
                "    minions_villains AS mv ON mv.villain_id = v.id\n" +
                "        JOIN\n" +
                "    minions AS m ON m.id = mv.minion_id\n" +
                "GROUP BY v.id\n" +
                "HAVING `count_of_minions` >= 2\n" +
                "ORDER BY `count_of_minions` DESC;");

        statement.close();

        return statement.executeQuery();
    }

    private static Connection createConnectionToDatabase(String dbName) throws SQLException {
        String name = String.format("jdbc:mysql://localhost:3306/%s?useSSL=false", dbName);

        return DriverManager.getConnection(name, USER, PASSWORD);
    }
}
