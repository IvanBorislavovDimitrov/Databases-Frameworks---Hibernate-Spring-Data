package app.entities.dto;

import java.util.HashSet;
import java.util.Set;

public class UserWithSoldItems {


    private String firstName;

    private String lastName;

    private Set<SoldProduct> soldProducts;

    public UserWithSoldItems() {
        this.soldProducts = new HashSet<>();
    }

    public UserWithSoldItems(String firstName, String lastName, Set<SoldProduct> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
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

    public Set<SoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<SoldProduct> soldProducts) {
        this.soldProducts = soldProducts;
    }
}

