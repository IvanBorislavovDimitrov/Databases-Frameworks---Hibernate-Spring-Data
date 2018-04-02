package core;

import entities.Car;
import entities.Truck;
import entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Vehicle car = new Car();
        Vehicle truck = new Truck();

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.persist(truck);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
