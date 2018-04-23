package app.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "lens")
public class Len {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String make;

    @Column(name = "focal_length")
    private int focalLength;

    @Column(name = "max_aperture")
    private BigDecimal maxAperture;

    @Column(name = "compatible_with")
    private String compatibleWith;

    @ManyToOne
    @JoinColumn(name = "photographer_id", referencedColumnName = "id")
    private Photographer photographer;

    public Len() {
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

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }

    public BigDecimal getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(BigDecimal maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }
}
