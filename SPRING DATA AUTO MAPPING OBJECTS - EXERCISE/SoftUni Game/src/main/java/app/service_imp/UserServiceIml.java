package app.service_imp;

import app.models.entities.Game;
import app.models.entities.Role;
import app.models.entities.User;
import app.models.entities.dtos.binding.UserRegistrationDto;
import app.models.entities.dtos.view.UserFullNameIdIsAdministrator;
import app.models.entities.dtos.view.UserFullNameWithId;
import app.models.entities.dtos.view.UserGames;
import app.repositories.UserRepository;
import app.services.GameService;
import app.services.RoleService;
import app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceIml implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final GameService gameService;
    private final RoleService roleService;

    @Autowired
    public UserServiceIml(UserRepository userRepository, GameService gameService, RoleService roleService) {
        this.userRepository = userRepository;
        this.gameService = gameService;
        this.roleService = roleService;
        this.mapper = new ModelMapper();
    }

    @Override
    public UserFullNameWithId register(UserRegistrationDto userRegistrationDto) {
        User user = this.mapper.map(userRegistrationDto, User.class);

        if (this.userRepository.count() == 0L) {
            Role administrator = this.roleService.getByName("Administrator");
            user.getRoles().add(administrator);
            administrator.getUsers().add(user);
        } else {
            Role normalUser = this.roleService.getByName("User");
            user.getRoles().add(normalUser);
            normalUser.getUsers().add(user);
        }

        User a = this.userRepository.saveAndFlush(user);
        return new UserFullNameWithId(a.getFullName(), a.getId());
    }

    @Override
    public UserFullNameWithId findByEmailAndPassword(String email, String password) {

        User user = this.userRepository.findByEmailAndPassword(email, password);

        if (user == null) {
            return null;
        }

        UserFullNameWithId userFullNameWithId = this.mapper.map(user, UserFullNameWithId.class);

        return userFullNameWithId;
    }

    @Override
    public UserFullNameWithId findByEmail(String email) {
        List<User> allUsers = this.userRepository.findAll();
        for (User user : allUsers) {
            if (user.getEmail().equals(email)) {
                return this.mapper.map(user, UserFullNameWithId.class);
            }
        }

        return null;
    }

    @Override
    public UserFullNameWithId getById(Long id) {
        return this.mapper.map(this.userRepository.getById(id), UserFullNameWithId.class);
    }

    @Override
    public UserFullNameIdIsAdministrator getUserById(Long id) {
        User user = this.userRepository.getById(id);
        int role = this.userRepository.getRole(id);
        UserFullNameIdIsAdministrator userFullNameIdIsAdministrator = this.mapper.map(user, UserFullNameIdIsAdministrator.class);
        if (role == 1) {
            userFullNameIdIsAdministrator.setAdministrator(true);
        }

        return userFullNameIdIsAdministrator;
    }

    @Override
    public UserGames getAllGames(Long userId) {
        User user = this.userRepository.getUserById(userId);

        if (user.getGames() == null) {
            return null;
        }

        UserGames userGames = new UserGames();
        userGames.setGames(user.getGames());

        return userGames;
    }

    @Override
    public void addItem(String gameName, Long id) {
        User currentUser = this.userRepository.getById(id);
        Game game = this.gameService.getByTitle(gameName);

        if (currentUser.getGames().contains(game)) {
            throw new IllegalArgumentException("User owns this game!");
        }
        if (currentUser.getOrders().contains(game)) {
            throw new IllegalArgumentException("This game is in shopping cart!");
        }

        currentUser.getOrders().add(game);
        game.getBuyersInProgress().add(currentUser);
    }

    @Override
    public void deleteOrderByName(String gameName, Long id) {
        User currentUser = this.userRepository.getById(id);
        Game game = this.gameService.getByTitle(gameName);
        if (! currentUser.getOrders().contains(game)) {
            throw new IllegalArgumentException("This game isn't in the shopping cart of this user!");
        } else {
            currentUser.getOrders().remove(game);
            game.getBuyersInProgress().remove(currentUser);
            System.out.println(gameName + " removed from cart!");
        }
    }

    @Override
    public List<String> purchase(Long id) {
        List<String> games = new ArrayList<>();
        User currentUser = this.userRepository.getById(id);
        currentUser.getGames().addAll(currentUser.getOrders());
        for (Game game : currentUser.getGames()) {
            game.getBuyersInProgress().remove(currentUser);
            games.add(game.getTitle());
        }
        currentUser.setOrders(new HashSet<>());

        return games;
    }
}
