package core;

import entities.Customer;
import entities.Product;
import entities.Sale;
import entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gringotts");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Customer customer = new Customer("Dragan", "1459364");

        Product product = new Product("rikiq", 7);

        StoreLocation location = new StoreLocation("Pazardzhik");

        Sale sale = new Sale(product, customer, location, new Date());

        entityManager.persist(customer);
        entityManager.persist(product);
        entityManager.persist(location);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
