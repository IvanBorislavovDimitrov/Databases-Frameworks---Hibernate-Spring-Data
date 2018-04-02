package entities;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "bet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private double betMoney;

    @Column(name = "date_time_of_bet")
    private Timestamp dateTimeOfBet;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "bet")
    private BetGame betGame;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(double betMoney) {
        this.betMoney = betMoney;
    }

    public Timestamp getDateTimeOfBet() {
        return dateTimeOfBet;
    }

    public void setDateTimeOfBet(Timestamp dateTimeOfBet) {
        this.dateTimeOfBet = dateTimeOfBet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BetGame getBetGame() {
        return betGame;
    }

    public void setBetGame(BetGame betGame) {
        this.betGame = betGame;
    }
}
