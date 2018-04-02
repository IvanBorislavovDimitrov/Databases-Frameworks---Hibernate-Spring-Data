package entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "visitation")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    private Date date;

    @ElementCollection
    @CollectionTable(name="comments_for_visitaiton", joinColumns=@JoinColumn(name="id"))
    @Column(name="comment_id")
    private List<String> comments;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public Visitation() {
        this.comments = new ArrayList<>();
    }

    public Visitation(Date date, Patient patient) {
        this.date = date;
        this.patient = patient;
        this.comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
