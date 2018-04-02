package core;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FindLatestTenProjects {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Project> projects = entityManager.createNativeQuery("SELECT * FROM projects ORDER BY project_id DESC LIMIT 10",
                Project.class).getResultList();

        for (Project project : projects.stream().sorted((x1, x2)  -> x1.getName().compareTo(x2.getName())).toArray(Project[]::new)) {
            System.out.println(String.format("Project name: %s", project.getName()));
            System.out.println(String.format("\tProject Description: %s", project.getDescription()));
            System.out.println(String.format("\tProject Start Date: %s", project.getStartDate()));
            System.out.println(String.format("\tProject End Date: %s", project.getEndDate()));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
