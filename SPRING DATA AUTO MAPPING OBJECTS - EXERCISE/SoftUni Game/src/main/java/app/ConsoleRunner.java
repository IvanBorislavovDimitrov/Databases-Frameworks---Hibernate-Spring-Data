package app;

import app.models.entities.Game;
import app.models.entities.dtos.binding.GameRegistrationDto;
import app.models.entities.dtos.binding.UserRegistrationDto;
import app.models.entities.dtos.view.*;
import app.services.GameService;
import app.services.RoleService;
import app.services.UserService;
import app.validations.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final GameService gameService;
    private Long loggedUserId;

    @Autowired
    public ConsoleRunner(UserService userService, RoleService roleService, GameService gameService) {
        this.userService = userService;
        this.roleService = roleService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        operate();
    }

    private void operate() throws IOException, ParseException {
        this.roleService.initializeRoles();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (! (line = input.readLine()).equals("end")) {
            String[] commandInfo = line.split("\\|");
            String command = commandInfo[0];

            switch (command) {
                case "RegisterUser":
                    registerUser(commandInfo);
                    break;
                case "LoginUser":
                    loginUser(commandInfo);
                    break;
                case "LogoutUser":
                    logoutUser();
                    break;
                case "AddGame":
                    addGame(commandInfo);
                    break;
                case "EditGame":
                    editGame(commandInfo);
                    break;
                case "DeleteGame":
                    deleteGame(commandInfo);
                    break;
                case "AllGame":
                    viewAllGames();
                    break;
                case "DetailGame":
                    viewGame(commandInfo);
                    break;
                case "OwnedGame":
                    printOwnedGames();
                    break;
                case "AddItem":
                    addItem(commandInfo);
                    break;
                case "RemoveItem":
                    removeItem(commandInfo);
                    break;
                case "BuyItem":
                    purchase();
                    break;
            }
        }
    }

    private void purchase() {
        if (this.loggedUserId == null) {
            System.out.println("Log in first!");
            return;
        }

        List<String> games = this.userService.purchase(this.loggedUserId);
        System.out.println("Successfully bought games:");
        for (String game : games) {
            System.out.println("-" + game);
        }
    }

    private void removeItem(String[] commandInfo) {
        String gameName = commandInfo[1];

        if (this.loggedUserId == null) {
            System.out.println("Log in first!");
            return;
        }

        try {
            this.userService.deleteOrderByName(gameName, this.loggedUserId);
            System.out.println("Game has been deleted!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addItem(String[] commandInfo) {
        String gameName = commandInfo[1];

        if (this.loggedUserId == null) {
            System.out.println("Log in first!");
            return;
        }

        try {
            this.userService.addItem(gameName, this.loggedUserId);
            System.out.println("Game has been added!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printOwnedGames() {
        if (this.loggedUserId == null) {
            System.out.println("You are not logged in!");
        } else {
            UserGames userGames = this.userService.getAllGames(this.loggedUserId);
            for (Game game :userGames.getGames()) {
                System.out.println(game.getTitle());
            }
        }
    }

    private void viewGame(String[] commandInfo) {
        String gameName = commandInfo[1];
        List<GameDetailedDto> gameDetailedDtos = this.gameService.getGamesWithName(gameName);
        for (GameDetailedDto gameDetailedDto : gameDetailedDtos) {
            System.out.println(gameDetailedDto);
        }
    }

    private void viewAllGames() {
        List<GameTitleWithPrice> gameTitleWithPrices = this.gameService.getAllGames();
        for (GameTitleWithPrice gameTitleWithPrice : gameTitleWithPrices) {
            System.out.println(gameTitleWithPrice);
        }
    }

    private void deleteGame(String[] commandInfo) {
        if (this.loggedUserId == null) {
            System.out.println("You must be logged in to add/edit games!");
        } else {
            UserFullNameIdIsAdministrator userFullNameIdIsAdministrator = this.userService.getUserById(this.loggedUserId);
            if (userFullNameIdIsAdministrator.isAdministrator()) {
                Long id = Long.parseLong(commandInfo[1]);
                this.gameService.deleteById(id);
                System.out.println("Game has been deleted!");
            } else {
                System.out.println("You are not administrator!");
            }
        }
    }

    private void editGame(String[] commandInfo) throws ParseException {
        if (this.loggedUserId == null) {
            System.out.println("You must be logged in to add/edit games!");
        } else {
            UserFullNameIdIsAdministrator userFullNameIdIsAdministrator = this.userService.getUserById(this.loggedUserId);
            if (userFullNameIdIsAdministrator.isAdministrator()) {
                Long gameId = Long.parseLong(commandInfo[1]);
                List<String> values = Arrays.stream(commandInfo).skip(2).collect(Collectors.toList());
                this.gameService.editGame(values, gameId);
                System.out.println("Game has been edited!");
            } else {
                System.out.println("You are not administrator!");
            }
        }
    }

    private void addGame(String[] commandInfo) throws ParseException {
        if (this.loggedUserId == null) {
            System.out.println("You must be logged in to add/edit games!");
        } else {
            UserFullNameIdIsAdministrator userFullNameIdIsAdministrator = this.userService.getUserById(this.loggedUserId);
            if (userFullNameIdIsAdministrator.isAdministrator()) {
                String title = commandInfo[1];
                BigDecimal price = new BigDecimal(commandInfo[2]);
                double size = Double.parseDouble(commandInfo[3]);
                String youtubeVideo = commandInfo[4];
                String thumbnailUrl = commandInfo[5];
                String description = commandInfo[6];
                Date releaseDate = new SimpleDateFormat("dd-MM-yyyy").parse(commandInfo[7]);

                try {
                    GameRegistrationDto gameRegistrationDto = new GameRegistrationDto(title, price, size, youtubeVideo,
                            thumbnailUrl, description, releaseDate);

                    this.gameService.register(gameRegistrationDto);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("You are not administrator!");
            }
        }
    }

    private void logoutUser() {
        if (this.loggedUserId == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            UserFullNameWithId userFullNameWithId = this.userService.getById(this.loggedUserId);
            this.loggedUserId = null;
            System.out.println(String.format("User %s successfully logged out", userFullNameWithId.getFullName()));
        }
    }

    private void loginUser(String[] commandInfo) {
        String email = commandInfo[1];
        String password = commandInfo[2];

        if (this.loggedUserId != null) {
            System.out.println("There is a logged user, please logout first!");
            return;
        }

        EmailValidator emailValidator = new EmailValidator();
        if (! emailValidator.isValid(email, null)) {
            System.out.println("Invalid email!");
            return;
        }

        UserFullNameWithId userFullNameWithId = this.userService.findByEmailAndPassword(email, password);

        if (userFullNameWithId != null) {
            System.out.println(String.format("Successfully logged in %s", userFullNameWithId.getFullName()));
            this.loggedUserId = userFullNameWithId.getId();
        } else {
            System.out.println("Incorrect username / password");
        }
    }

    private void registerUser(String[] commandInfo) {
        String email = commandInfo[1];
        String password = commandInfo[2];
        String confirmPassword = commandInfo[3];
        String fullName = commandInfo[4];

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail(email);
        userRegistrationDto.setPassword(password);
        userRegistrationDto.setConfirmPassword(confirmPassword);
        userRegistrationDto.setFullName(fullName);

        if (! userRegistrationDto.getConfirmPassword().equals(userRegistrationDto.getPassword())) {
            System.out.println("Incorrect password!");
            return;
        }

        if (this.userService.findByEmail(email) != null) {
            System.out.println("This user already exists!");
            return;
        }

        EmailValidator emailValidator = new EmailValidator();
        if (! emailValidator.isValid(email, null)) {
            System.out.println("Invalid email!");
            return;
        }

        UserFullNameWithId userFullNameWithId = this.userService.register(userRegistrationDto);

        if (userFullNameWithId.getId() != null) {
            System.out.println(String.format("%s was registered", userFullNameWithId.getFullName()));
        } else {
            System.out.println("User isn't registered");
        }

    }
}
