package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "employee_cards")
public class EmployeeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private Employee employee;

    public EmployeeCard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
