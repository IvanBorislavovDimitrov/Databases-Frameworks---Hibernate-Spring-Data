package app.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workshops")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(nullable = false)
    @NotNull
    private String location;

    @Column(name = "price_per_participants", nullable = false)
    @NotNull
    private BigDecimal pricePerParticipant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Photographer trainer;

    @ManyToMany
    @JoinTable(name = "workshops_participants",
    joinColumns = @JoinColumn(name = "workshop_id"),
    inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private Set<Photographer> participants;


    public Workshop() {
        this.participants = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public Photographer getTrainer() {
        return trainer;
    }

    public void setTrainer(Photographer trainer) {
        this.trainer = trainer;
    }

    public Set<Photographer> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Photographer> participants) {
        this.participants = participants;
    }
}
