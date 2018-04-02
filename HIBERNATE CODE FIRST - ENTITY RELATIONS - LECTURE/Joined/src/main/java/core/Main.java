package core;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();

        Vehicle car = new Car();
        Vehicle truck = new Truck();

        entityManager.persist(car);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();
    }
}
