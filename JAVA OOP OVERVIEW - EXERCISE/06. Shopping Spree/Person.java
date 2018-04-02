package shoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Person {

    private String name;
    private double money;
    private List<Product> bagOfProducts;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.bagOfProducts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public List<Product> getBagOfProducts() {
        return bagOfProducts;
    }

    public void setBagOfProducts(List<Product> bagOfProducts) {
        this.bagOfProducts = bagOfProducts;
    }
}