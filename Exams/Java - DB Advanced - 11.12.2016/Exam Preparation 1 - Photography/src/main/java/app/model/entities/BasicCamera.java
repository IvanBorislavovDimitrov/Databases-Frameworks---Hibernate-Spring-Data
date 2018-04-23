package app.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "basic_camera")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicCamera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String make;

    @Column(nullable = false)
    @NotNull
    private String model;

    @Column(name = "is_full_name")
    private boolean isFullFrame;

    @Min(100)
    @NotNull
    @Column(name = "min_iso", nullable = false)
    private Integer minIso;

    @Column(name = "max_iso")
    private Integer maxIso;

    @OneToMany(mappedBy = "primaryCamera")
    private Set<Photographer> primaryCameraPhotographers;

    @OneToMany(mappedBy = "secondaryCamera")
    private Set<Photographer> secondaryCameraPhotographers;

    public BasicCamera() {
        this.primaryCameraPhotographers = new HashSet<>();
        this.secondaryCameraPhotographers = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isFullFrame() {
        return isFullFrame;
    }

    public void setFullFrame(boolean fullFrame) {
        isFullFrame = fullFrame;
    }

    public Integer getMinIso() {
        return minIso;
    }

    public void setMinIso(Integer minIso) {
        this.minIso = minIso;
    }

    public Integer getMaxIso() {
        return maxIso;
    }

    public void setMaxIso(Integer maxIso) {
        this.maxIso = maxIso;
    }

    public Set<Photographer> getPrimaryCameraPhotographers() {
        return primaryCameraPhotographers;
    }

    public void setPrimaryCameraPhotographers(Set<Photographer> primaryCameraPhotographers) {
        this.primaryCameraPhotographers = primaryCameraPhotographers;
    }

    public Set<Photographer> getSecondaryCameraPhotographers() {
        return secondaryCameraPhotographers;
    }

    public void setSecondaryCameraPhotographers(Set<Photographer> secondaryCameraPhotographers) {
        this.secondaryCameraPhotographers = secondaryCameraPhotographers;
    }
}

