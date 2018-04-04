package app.services;

import app.models.entities.Game;
import app.models.entities.User;
import app.models.entities.dtos.binding.GameRegistrationDto;
import app.models.entities.dtos.view.GameDetailedDto;
import app.models.entities.dtos.view.GameTitleWithPrice;
import app.models.entities.dtos.view.UserGames;
import org.springframework.data.repository.query.Param;

import java.text.ParseException;
import java.util.List;

public interface GameService {

    void register(GameRegistrationDto gameRegistrationDto);

    GameRegistrationDto editGame(List<String> values, Long id) throws ParseException;

    void deleteById(Long id);

    List<GameTitleWithPrice> getAllGames();

    List<GameDetailedDto> getGamesWithName(String title);

    Game getByTitle(String title);


}
