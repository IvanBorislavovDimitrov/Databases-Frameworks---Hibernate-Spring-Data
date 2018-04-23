package app.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Pattern(regexp = "^\\+[0-9]{1,3}\\/[0-9]{8,10}$")
    private String phone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "primary_camera_id", referencedColumnName = "id")
    private BasicCamera primaryCamera;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "secondary_camera_id", referencedColumnName = "id")
    private BasicCamera secondaryCamera;

    @OneToMany(mappedBy = "owner")
    private Set<Accessory> accessories;

    @OneToMany(mappedBy = "photographer")
    private Set<Len> lens;

    @ManyToMany(mappedBy = "participants")
    private Set<Workshop> workshops;

    @OneToMany(mappedBy = "trainer")
    private Set<Workshop> workshopsThatLeads;

    public Photographer() {
        this.accessories = new HashSet<>();
        this.lens = new HashSet<>();
        this.workshops = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasicCamera getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(BasicCamera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public BasicCamera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void setSecondaryCamera(BasicCamera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public Set<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Set<Len> getLens() {
        return lens;
    }

    public void setLens(Set<Len> lens) {
        this.lens = lens;
    }

    public Set<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(Set<Workshop> workshops) {
        this.workshops = workshops;
    }
}
