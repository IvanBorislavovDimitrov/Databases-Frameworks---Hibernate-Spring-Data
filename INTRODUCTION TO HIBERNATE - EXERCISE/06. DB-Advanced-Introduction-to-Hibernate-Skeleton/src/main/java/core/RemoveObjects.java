package core;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RemoveObjects {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Town> towns = entityManager.createNativeQuery("select * from towns;", Town.class).getResultList();

        for (Town town1 : towns) {
            if (town1.getName().length() > 5) {
                entityManager.remove(town1);
            }
        }

        refactorTowns(towns);

        for (Town town : towns) {
            entityManager.persist(town);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        printTowns(towns);
    }

    private static void printTowns(List<Town> towns) {
        for (Town newTown : towns) {
            System.out.println(newTown.getId() + " " + newTown.getName());
        }
    }

    private static void refactorTowns(List<Town> towns) {
        for (Town town : towns) {
            if (town.getName().length() > 5) {
                town.setName(town.getName().toLowerCase());
            }
        }
    }
}
