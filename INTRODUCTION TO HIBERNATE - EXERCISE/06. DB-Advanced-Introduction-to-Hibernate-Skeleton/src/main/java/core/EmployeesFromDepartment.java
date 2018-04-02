package core;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Employee> employees = entityManager.createNativeQuery("select * from employees as e\n" +
                "join departments as d on e.department_id = d.department_id\n" +
                "where d.name='Research and Development'\n" +
                "order by e.salary;\n", Employee.class).getResultList();


        for (Employee employee : employees) {
            System.out.println(String.format("%s %s from %s - $%.2f", employee.getFirstName(),
                    employee.getLastName(), employee.getDepartment().getName(), employee.getSalary()));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
