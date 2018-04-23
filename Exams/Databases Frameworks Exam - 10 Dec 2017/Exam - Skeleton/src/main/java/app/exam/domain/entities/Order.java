package app.exam.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer", nullable = false)
    @NotNull
    private String customer;

    @Column(name = "date", nullable = false)
    @NotNull
    private Date date;

    @Column(nullable = false, name = "type")
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType;

    @Transient
    @Column(nullable = false)
    @NotNull
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @NotNull
    private Employee employee;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    public Order() {
        this.orderType = OrderType.ForHere;
        this.orderItems = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
