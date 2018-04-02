package core;

import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class EmployeesMaximumSalaries {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Department> departments = entityManager.createNativeQuery("SELECT \n" +
                "    *\n" +
                "FROM\n" +
                "    employees AS e\n" +
                "        JOIN\n" +
                "    departments AS d ON d.department_id = e.department_id\n" +
                "GROUP BY d.department_id\n" +
                "HAVING MAX(e.salary) < 30000\n" +
                "    OR MAX(e.salary) > 70000;", Department.class).getResultList();

        for (Department department : departments) {
            BigDecimal max = BigDecimal.ZERO;
            for (BigDecimal num : department.getEmployees().stream().map(Employee::getSalary).toArray(BigDecimal[]::new)) {
                if (max.compareTo(num) < 0) {
                    max = num;
                }
            }
            System.out.println(String.format("%s - %s", department.getName(), max));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
