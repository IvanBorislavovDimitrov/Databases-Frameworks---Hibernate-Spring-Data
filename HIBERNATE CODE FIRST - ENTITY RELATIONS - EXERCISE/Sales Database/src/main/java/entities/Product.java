package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Basic
    private String name;

    @Column(name = "quantity_price")
    private int quantityPrice;

    @OneToMany(mappedBy = "product", targetEntity = Sale.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sale> sales;

    public Product() {
        this.sales = new HashSet<>();
    }

    public Product(String name, int quantityPrice) {
        this.name = name;
        this.quantityPrice = quantityPrice;
        this.sales = new HashSet<>();
    }

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

    public int getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(int quantityPrice) {
        this.quantityPrice = quantityPrice;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
