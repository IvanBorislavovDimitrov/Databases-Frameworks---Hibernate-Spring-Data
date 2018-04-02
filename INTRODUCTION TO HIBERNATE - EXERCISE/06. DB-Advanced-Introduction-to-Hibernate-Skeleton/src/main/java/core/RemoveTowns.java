package core;

import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.print("Enter town's name: ");
        String townName = new Scanner(System.in).nextLine().toLowerCase();
        List<Town> towns = entityManager.createNativeQuery("SELECT * FROM towns", Town.class).getResultList(); // gets all towns
        int townId = getTownId(towns, townName);

        List<Address> addresses = entityManager.createNativeQuery("SELECT * FROM addresses WHERE town_id =" + townId + ";",
                Address.class).getResultList(); // gets all addresses

        List<Integer> idsToNull = getAllAddressesIds(entityManager, townId); // all addresses' ids which are located in the searched city
        nullEmployeesIdByCriteria(entityManager, idsToNull); // null addresses_ids of employees whose address is in searched city

        entityManager.createNativeQuery("DELETE FROM addresses WHERE town_id = " + townId + ";").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM towns where town_id = " + townId + ";").executeUpdate();
        System.out.println(String.format("%d address in %s deleted", addresses.size(), townName));

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void nullEmployeesIdByCriteria(EntityManager entityManager, List<Integer> ids) {
        for (Integer id : ids) {
            entityManager.createNativeQuery("UPDATE employees SET address_id = NULL WHERE address_id = " + id).executeUpdate();
        }
    }

    private static List<Integer> getAllAddressesIds(EntityManager entityManager, int townId) {
        List<Address> addresses = entityManager.createNativeQuery("SELECT * FROM addresses WHERE town_id=" + townId + ";",
                Address.class).getResultList();
        List<Integer> ids = new ArrayList<>();
        for (Address address : addresses) {
            ids.add(address.getId());
        }

        return ids;
    }

    private static int getTownId(List<Town> towns, String townName) {
        for (Town town : towns) {
            if (town.getName().equals(townName)) {
                return town.getId();
            }
        }

        return -1;
    }
}
