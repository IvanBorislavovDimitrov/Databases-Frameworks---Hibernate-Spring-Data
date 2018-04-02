package app;

import app.entities.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final String AUTHORS_FILE = "C:\\Users\\Ivan\\Downloads\\Skeleton\\folder\\SoftUni_Databases_Frameworks_Hibernate_-_Spring_Data\\8.1 Spring Data Intro\\demo\\src\\main\\resources\\authors.txt";
    private int startYear = 100;

    private UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.fillUsers();
        this.getUsersByEmailProvider("gmail.com");
        this.userService.removeInactiveUsers(new Timestamp(new Date(this.startYear - 3, 11, 4).getTime()));
        this.userService.deleteAllByIsDeleted(1);
    }

    private void getUsersByEmailProvider(String provider) {
        List<User> users = this.userService.findAll();
        for (User user : users) {
            if (user.getEmail().endsWith(provider)) {
                System.out.println(String.format("%s %s", user.getUsername(), user.getEmail()));
            }
        }
    }

    private void fillUsers() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(AUTHORS_FILE));

        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            String[] userInfo = line.split("\\s+");
            String firstName = userInfo[0];
            String lastName = userInfo[1];

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(12);
            user.setDeleted(0);
            user.setUsername(String.format("%s_%s%d", firstName, lastName, new Random().nextInt(100000)));
            if (count < 10) {
                user.setEmail(String.format("%s_%s@gmail.com", firstName, lastName));
            } else if (count < 20) {
                user.setEmail(String.format("%s_%s@abv.com", firstName, lastName));
            } else {
                user.setEmail(String.format("%s_%s@crazy.co.uk", lastName, firstName));
            }

            user.setPassword(firstName.charAt(0) + "" + lastName.charAt(0) + "1Aa1231231!@#Msd");
            user.setDeleted(0);

            user.setLastTimeLoggedIn(new Timestamp(new Date(this.startYear, 11, 4).getTime()));
            user.setRegisterOn(new Timestamp(new Date(this.startYear, 3, 4).getTime()));
            count++;
            if (count % 3 == 0) {
                this.startYear++;
            }

            this.userService.register(user);
        }
    }
}
