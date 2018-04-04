package app.service_imp;

import app.models.entities.Game;
import app.models.entities.User;
import app.models.entities.dtos.binding.GameRegistrationDto;
import app.models.entities.dtos.view.GameDetailedDto;
import app.models.entities.dtos.view.GameTitleWithPrice;
import app.models.entities.dtos.view.UserGames;
import app.repositories.GameRepository;
import app.services.GameService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImp implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper mapper;

    @Autowired
    public GameServiceImp(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.mapper = new ModelMapper();
        initializeMapper();
    }

    private void initializeMapper() {
        PropertyMap<GameRegistrationDto, Game> gameRegistrationDtoGamePropertyMap = new PropertyMap<GameRegistrationDto, Game>() {
            @Override

            protected void configure() {
                map().setPrice(source.getPrice());
                map().setDescription(source.getDescription());
                map().setImageThumbnail(source.getImageThumbnail());
                map().setReleaseDate(source.getReleaseDate());
                map().setSize(source.getSize());
                map().setTitle(source.getTitle());
            }
        };
        this.mapper.addMappings(gameRegistrationDtoGamePropertyMap);
    }


    @Override
    public void register(GameRegistrationDto gameRegistrationDto) {
        Game game = this.mapper.map(gameRegistrationDto, Game.class);

        this.gameRepository.saveAndFlush(game);
    }

    @Override
    public GameRegistrationDto editGame(List<String> values, Long id) throws ParseException {
        Game game = this.gameRepository.getOne(id);
        for (String value : values) {
            editAttribute(value, game);
        }

        this.gameRepository.saveAndFlush(game);

        return this.mapper.map(game, GameRegistrationDto.class);
    }

    @Override
    public void deleteById(Long id) {
        this.gameRepository.deleteById(id);
    }

    @Override
    public List<GameTitleWithPrice> getAllGames() {
        List<Game> games = this.gameRepository.findAll();
        List<GameTitleWithPrice> gameTitleWithPrices = new ArrayList<>();
        for (Game game : games) {
            GameTitleWithPrice gameTitleWithPrice = this.mapper.map(game, GameTitleWithPrice.class);
            gameTitleWithPrices.add(gameTitleWithPrice);
        }
        gameTitleWithPrices.sort(Comparator.comparing(GameTitleWithPrice::getTitle).thenComparing(GameTitleWithPrice::getPrice));

        return gameTitleWithPrices;
    }

    private void editAttribute(String attributeAndValue, Game game) throws ParseException {
        String[] infoAboutAttributeAndValue = attributeAndValue.split("=");
        String attribute = infoAboutAttributeAndValue[0];
        switch (attribute) {
            case "title":
                String titleName = infoAboutAttributeAndValue[1];
                game.setTitle(titleName);
                break;
            case "price":
                BigDecimal price = new BigDecimal(infoAboutAttributeAndValue[1]);
                game.setPrice(price);
                break;
            case "size":
                double size = Double.parseDouble(infoAboutAttributeAndValue[1]);
                game.setSize(size);
                break;
            case "trailer":
                String trailer = infoAboutAttributeAndValue[1];
                game.setYoutubeVideo(trailer);
                break;
            case "thumbnailUrl":
                String thumbnailUrl = infoAboutAttributeAndValue[1];
                game.setImageThumbnail(thumbnailUrl);
                break;
            case "description":
                String description = infoAboutAttributeAndValue[1];
                game.setDescription(description);
                break;
            case "releaseDate":
                Date releaseDate = new SimpleDateFormat("dd-MM-yyyy").parse(infoAboutAttributeAndValue[1]);
                game.setReleaseDate(releaseDate);
                break;
        }
    }

    @Override
    public List<GameDetailedDto> getGamesWithName(String title) {
        List<Game> games = this.gameRepository.findByTitle(title);
        List<GameDetailedDto> gameDetailedDtos = new ArrayList<>();

        for (Game game : games) {
            gameDetailedDtos.add(this.mapper.map(game, GameDetailedDto.class));
        }

        gameDetailedDtos.sort(Comparator.comparing(GameDetailedDto::getTitle));

        return gameDetailedDtos;
    }

    @Override
    public Game getByTitle(String title) {
        return this.gameRepository.getByTitle(title);
    }


}
