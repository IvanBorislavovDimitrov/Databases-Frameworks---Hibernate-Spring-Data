package core;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String pattern = input.nextLine();
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createNativeQuery("select * from employees;", Employee.class).getResultList();
        for (Employee employee : employees) {
            if (employee.getFirstName().toLowerCase().startsWith(pattern.toLowerCase())) {
                System.out.println(String.format("%s %s - %s - ($%s)", employee.getFirstName(),
                        employee.getLastName(), employee.getJobTitle(), employee.getSalary().setScale(2, RoundingMode.CEILING)));
            }
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
