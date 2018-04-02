package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table (name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Size(min = 2, max = 2)
    private String id;

    @Basic
    private String positionFullName;

    @OneToMany(mappedBy = "position", targetEntity = Player.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Player> players;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionFullName() {
        return positionFullName;
    }

    public void setPositionFullName(String positionFullName) {
        this.positionFullName = positionFullName;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
