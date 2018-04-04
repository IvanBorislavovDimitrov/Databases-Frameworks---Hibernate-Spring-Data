package app.services;

import app.models.entities.User;
import app.models.entities.dtos.binding.UserRegistrationDto;
import app.models.entities.dtos.view.UserFullNameIdIsAdministrator;
import app.models.entities.dtos.view.UserFullNameWithId;
import app.models.entities.dtos.view.UserGames;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {

    UserFullNameWithId register(UserRegistrationDto UserRegistrationDto);

    UserFullNameWithId findByEmailAndPassword(String email, String password);

    UserFullNameWithId findByEmail(String email);

    UserFullNameWithId getById(Long id);

    UserFullNameIdIsAdministrator getUserById(Long id);

    UserGames getAllGames(@Param("user_id") Long userId);

    void addItem(String gameName, Long id);

    void deleteOrderByName(String gameName, Long id);

    List<String> purchase(Long id);
}
