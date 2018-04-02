import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions?useSSL=false")) {
            String sql = "create table countries (\n" +
                    "\tid int primary key auto_increment,\n" +
                    "    `name` varchar(100) not null\n" +
                    ");\n" +
                    "\n" +
                    "create table towns (\n" +
                    "\tid int primary key auto_increment,\n" +
                    "    `name` varchar(100) not null,\n" +
                    "    country_id int not null,\n" +
                    "    CONSTRAINT fk_towns_countries FOREIGN KEY (country_id) REFERENCES countries(id)\n" +
                    ");\n" +
                    "\n" +
                    "create table minions(\n" +
                    "\tid int primary key AUTO_INCREMENT,\n" +
                    "    `name` varchar(50) not null,\n" +
                    "    age int not null,\n" +
                    "    town_id int,\n" +
                    "    CONSTRAINT fk_minions_towns FOREIGN KEY (town_id) REFERENCES towns(id)\n" +
                    ");\n" +
                    "\n" +
                    "create table villains (\n" +
                    "\tid int primary key auto_increment,\n" +
                    "    `name` varchar(100) not null,\n" +
                    "    evilness_factor enum ('good', 'bad', 'evil', 'super evil')\n" +
                    ");\n" +
                    "\n" +
                    "create table minions_villains (\n" +
                    "\tminion_id int not null,\n" +
                    "    villain_id int not null,\n" +
                    "    PRIMARY KEY (minion_id, villain_id),\n" +
                    "    CONSTRAINT fk_minions_villains_minions FOREIGN KEY (minion_id) REFERENCES minions(id),\n" +
                    "    CONSTRAINT fk_minions_villains_villains FOREIGN KEY (villain_id) REFERENCES villains(id)\n" +
                    ");\n" +
                    "\n" +
                    "insert into countries(id, `name`)\n" +
                    "values (1, 'Bulgaria'), (2, 'Serbia'), (3, 'Romania'), (4, 'Croatia'), (5, 'Greece');\n" +
                    "\n" +
                    "insert into towns(id, `name`, country_id)\n" +
                    "values (1, 'Pazardzhik', 1), (2, 'Beograd', 2), (3, 'Sinaia', 3), (4, 'Vraca', 1), (5, 'Athens', 5);\n" +
                    "\n" +
                    "insert into minions(id, age, `name`,  town_id)\n" +
                    "values (1, 21, 'Gogo piinqka', 1), (2, 22, 'Malinkata', 4), \n" +
                    "(3, 66, 'Mile Kitic', 2), (4, 64, 'Vasilis Karras', 5), (5, 38, 'Florin Salam', 3);\n" +
                    "\n" +
                    "insert into villains(`name`, evilness_factor)\n" +
                    "values ('Petko', 'bad'), ('Mitko', 'good'), ('Pepi', 'super evil'), ('Hitler', 'good'), ('Karadzhic', 'bad');\n" +
                    "\n" +
                    "insert into minions_villains(minion_id, villain_id)\n" +
                    "values (1, 1), (2, 2),(3, 3),(4, 4),(5, 5);";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
