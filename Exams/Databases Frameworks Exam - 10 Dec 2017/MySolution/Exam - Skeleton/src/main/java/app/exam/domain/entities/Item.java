package app.exam.domain.entities;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 30)
    @NotNull
    private String name;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotNull
    private Category category;

    @Column(nullable = false, name = "price")
    @DecimalMin("0.01")
    @NotNull
    @Min(0)
    private BigDecimal price;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    public Item() {
        this.orderItems = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
