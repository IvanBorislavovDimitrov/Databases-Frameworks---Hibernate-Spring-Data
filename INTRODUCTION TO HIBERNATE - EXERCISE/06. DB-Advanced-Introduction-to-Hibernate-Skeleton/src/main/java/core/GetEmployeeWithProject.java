package core;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        int employeeId = Integer.parseInt(input.nextLine());
        Employee employee = entityManager.find(Employee.class, employeeId);

        System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getJobTitle());
        for (Project project : employee.getProjects().stream().sorted((x1, x2) -> x1.getName().compareTo(x2.getName())).toArray(Project[]::new)) {
            System.out.println(project.getName());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}

