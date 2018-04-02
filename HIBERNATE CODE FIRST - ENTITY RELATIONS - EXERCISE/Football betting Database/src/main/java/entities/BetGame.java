package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "bet_game")
public class BetGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(name = "games_bets",
            joinColumns = @JoinColumn(name = "bet_game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private Set<Game> games;

    @OneToOne(optional = false)
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    private Bet bet;

    @OneToOne(optional = false)
    @JoinColumn(name = "prediction_id", referencedColumnName = "id")
    private ResultPrediction prediction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getPrediction() {
        return prediction;
    }

    public void setPrediction(ResultPrediction prediction) {
        this.prediction = prediction;
    }
}
