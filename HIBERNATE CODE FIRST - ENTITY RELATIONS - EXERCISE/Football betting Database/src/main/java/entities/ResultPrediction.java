package entities;

import javax.persistence.*;

@Entity
@Table(name = "bet_game")
public class ResultPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String predictionName;

    @OneToOne(mappedBy = "prediction")
    private BetGame betGame;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPredictionName() {
        return predictionName;
    }

    public void setPredictionName(String predictionName) {
        this.predictionName = predictionName;
    }

    public BetGame getBetGame() {
        return betGame;
    }

    public void setBetGame(BetGame betGame) {
        this.betGame = betGame;
    }
}
