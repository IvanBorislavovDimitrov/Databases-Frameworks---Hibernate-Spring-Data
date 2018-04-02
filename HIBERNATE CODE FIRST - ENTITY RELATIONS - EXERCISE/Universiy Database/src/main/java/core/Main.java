package core;

import entities.Course;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("university");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Teacher teacher = new Teacher("Georgi", "Kostadinov", "0877591466", "gogo@abv.bg", 4);
        Teacher teacher1 = new Teacher("Petko", "Kostadinov", "0872391466", "Petko@abv.bg", 1);
        Teacher teacher2 = new Teacher("Mitko", "Kostadinov", "0843291466", "Mitko@abv.bg", 2);

        Course course = new Course("Programming basics with Java", "Course for begginers",
                new Date(), new Date(), 3, teacher);
        teacher.getCourses().add(course);

        Course course1 = new Course("Programming fundamentals with Java", "Course for begginers",
                new Date(), new Date(), 3, teacher1);
        teacher1.getCourses().add(course1);

        Student student = new Student("Pepi", "Kostadinov", "0877591412", 5.3, 10);
        student.getCourses().add(course);
        student.getCourses().add(course1);
        course.getStudents().add(student);
        course1.getStudents().add(student);

        entityManager.persist(teacher);
        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
        entityManager.persist(course);
        entityManager.persist(course1);
        entityManager.persist(student);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
