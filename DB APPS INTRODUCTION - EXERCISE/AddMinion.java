import java.sql.*;
import java.util.Scanner;

public class AddMinion {

    private static final String USER = "root";

    private static final String PASSWORD = "34273427";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Connection connection = null;

        try {
            connection = createConnectionToDatabase("minions");

            System.out.print("Minion (name, age, town): ");
            String[] infoAboutMinion = input.nextLine().split("\\s+");
            String nameOfMinion = infoAboutMinion[0];
            int ageOfMinion = Integer.parseInt(infoAboutMinion[1]);
            String nameOfTown = infoAboutMinion[2];
            System.out.print("Villain (name): ");
            String nameOfVillain = input.nextLine();

            int townId = getTownId(connection, nameOfTown);
            if (townId == -1) {
                townId = addTown(connection, nameOfTown, input);
            }

            int villainId = getVillainId(connection, nameOfVillain);
            if (villainId == -1) {
                villainId = addVillain(connection, nameOfVillain);
            }

            int minionId = addMinion(connection, nameOfMinion, ageOfMinion, townId);

            addVillainToMinion(connection, minionId, villainId);

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

    private static void addVillainToMinion(Connection connection, int minionId, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO minions_villains(minion_id, villain_id)\n" +
                "values(?, ?);");

        statement.setInt(1, minionId);
        statement.setInt(2, villainId);

        statement.execute();

        String minionName = getMinionName(connection, minionId);
        String villainName = getVillainName(connection, villainId);
        System.out.println(String.format("Successfully added %s to be minion of %s.", minionName, villainName));

        statement.close();
    }

    private static String getVillainName(Connection connection, int villainId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT \n" +
                "    *\n" +
                "FROM\n" +
                "    villains\n" +
                "WHERE\n" +
                "    id = ?;");

        statement.setInt(1, villainId);

        ResultSet minions = statement.executeQuery();
        if (minions.next()) {
            if (minions.getInt("id") == villainId) {
                return minions.getString("name");
            }
        }

        statement.close();

        return "";
    }

    private static String getMinionName(Connection connection, int minionId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT \n" +
                "    *\n" +
                "FROM\n" +
                "    minions\n" +
                "WHERE\n" +
                "    id = ?;");

        statement.setInt(1, minionId);

        ResultSet minions = statement.executeQuery();
        if (minions.next()) {
            if (minions.getInt("id") == minionId) {
                return minions.getString("name");
            }
        }

        statement.close();

        return "";
    }

    private static int getLastAddedMinionId(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from minions\n" +
                "order by id DESC\n" +
                "limit 1;");

        ResultSet minions = statement.executeQuery();
        if (minions.next()) {
            return minions.getInt("id");
        }

        statement.close();

        return -1;
    }

    private static int addMinion(Connection connection, String minionName, int minionAge, int townId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into minions(name, age, town_id)\n" +
                "values(?, ?, ?);");

        statement.setString(1, minionName);
        statement.setInt(2, minionAge);
        statement.setInt(3, townId);

        statement.execute();

        System.out.println("Minion was added the database.");

        statement.close();

        return getLastAddedMinionId(connection);
    }

    private static int addVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO villains(name, evilness_factor) " +
                "VALUES (?, ?)");

        System.out.println("This villain doesn't exist in the database.");

        statement.setString(1, villainName);
        statement.setString(2, "evil");

        statement.execute();

        System.out.println("Villain was added to the database.");

        statement.close();

        return getVillainId(connection, villainName);
    }

    private static int getVillainId(Connection connection, String villainName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM villains;");

        ResultSet villains = statement.executeQuery();
        while (villains.next()) {
            if (villainName.equals(villains.getString("name"))) {
                return villains.getInt("id");
            }
        }

        return -1;
    }

    private static int addTown(Connection connection, String townName, Scanner input) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into towns(`name`, country_id)\n" +
                "values(?, ?);");
        System.out.println("This town doesn't exist in the database. It will be added!");
        System.out.print("Enter name of country where this town is located: ");
        String countryName = input.nextLine();
        int countryId = getCountryId(connection, countryName);
        if (countryId == -1) {
            System.out.println("This country doesn't exist in the database. It will be added automatically.");
            countryId = addCountry(connection, countryName);
        }

        statement.setString(1, townName);
        statement.setInt(2, countryId);

        statement.execute();

        System.out.println("This town was added to the database.");

        statement.close();

        return getTownId(connection, townName);
    }

    private static int addCountry(Connection connection, String countryName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT into countries(name)\n" +
                "values(?);");
        statement.setString(1, countryName);

        statement.execute();

        System.out.println("This country was added to the database.");

        statement.close();

        return getCountryId(connection, countryName);
    }

    private static int getCountryId(Connection connection, String countryName) throws SQLException {
        String sql = "select * from countries;";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet countries = statement.executeQuery();
        while (countries.next()) {
            if (countryName.equals(countries.getString("name"))) {
                return countries.getInt("id");
            }
        }

        statement.close();

        return -1;
    }

    private static int getTownId(Connection connection, String nameOfTown) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT \n" +
                "    *\n" +
                "FROM\n" +
                "    towns;\n" +
                "\n");

        ResultSet towns = statement.executeQuery();
        while (towns.next()) {
            if (towns.getString("name").equals(nameOfTown)) {
                return towns.getInt("id");
            }
        }

        statement.close();

        return -1;
    }

    private static Connection createConnectionToDatabase(String dbName) throws SQLException {
        String name = String.format("jdbc:mysql://localhost:3306/%s?useSSL=false", dbName);

        return DriverManager.getConnection(name, USER, PASSWORD);
    }
}
