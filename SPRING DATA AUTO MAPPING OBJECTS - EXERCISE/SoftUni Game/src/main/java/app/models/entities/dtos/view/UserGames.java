package app.models.entities.dtos.view;

import app.models.entities.Game;

import java.util.List;
import java.util.Set;

public class UserGames {

    private Set<Game> games;

    public UserGames() {
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}

