package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competition")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    private String name;

    @OneToMany(mappedBy = "round", targetEntity = Game.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Game> games;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}