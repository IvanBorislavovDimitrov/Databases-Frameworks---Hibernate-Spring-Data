package core;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class IncreaseSalaries {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createNativeQuery("SELECT \n" +
                "    *\n" +
                "FROM\n" +
                "    employees AS e\n" +
                "        JOIN\n" +
                "    departments AS d ON d.department_id = e.department_id\n" +
                "WHERE\n" +
                "    d.name = 'Engineering'\n" +
                "        OR d.name = 'Tool Design'\n" +
                "        OR d.name = 'Marketing';", Employee.class).getResultList();

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(new BigDecimal(1.12)));
            entityManager.persist(employee);
            System.out.println(employee.getFirstName() + " " + employee.getLastName() + " ($" +
                    employee.getSalary().toString().substring(0, employee.getSalary().toString().indexOf("." ) + 3)+ ")");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
