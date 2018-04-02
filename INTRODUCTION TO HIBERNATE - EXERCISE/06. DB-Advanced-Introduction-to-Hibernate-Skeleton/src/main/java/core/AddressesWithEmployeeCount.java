package core;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressesWithEmployeeCount {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createNativeQuery("SELECT \n" +
                "    *, COUNT(e.employee_id) as count_of_employees\n" +
                "FROM\n" +
                "    addresses AS a\n" +
                "        JOIN\n" +
                "    employees AS e ON e.address_id = a.address_id\n" +
                "        JOIN\n" +
                "    towns AS t ON t.town_id = a.town_id\n" +
                "GROUP BY a.address_id\n" +
                "ORDER BY count_of_employees DESC , t.town_id ASC\n" +
                "LIMIT 10;", Employee.class).getResultList();

        for (Employee employee : employees) {
            System.out.println(String.format("%s %s - %d employees",
                    employee.getAddress().getText(), employee.getAddress().getTown().getName(), employee.getAddress().getEmployees().size()));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
