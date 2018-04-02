package core;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager.createNativeQuery("SELECT * FROM employee", Employee.class).getResultList();

        String searchedEmployee = input.nextLine();
        boolean flag = false;
        for (Employee employee : employees) {
            String name = employee.getFirstName() + " " + employee.getLastName();
            if (name.equals(searchedEmployee)) {
                System.out.println("Yes");
                flag = true;
            }
        }

        if (! flag) {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
