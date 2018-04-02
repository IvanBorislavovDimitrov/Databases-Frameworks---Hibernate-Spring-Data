package core;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddingNewAddressAndUpdatingEmployee {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(entityManager.find(Town.class, 32));
        entityManager.persist(address);

        List<Employee> employees = entityManager.createNativeQuery("select * from employees ORDER BY employee_id DESC;",
                Employee.class).getResultList();
        Employee searchedEmployee = employees.get(0);
        searchedEmployee.setAddress(address);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
