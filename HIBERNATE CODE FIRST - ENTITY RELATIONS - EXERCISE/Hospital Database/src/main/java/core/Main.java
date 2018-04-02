package core;

import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hospital");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Patient patient = new Patient("Petko", "Mitkov", "Sofia - Aleksandar Malinov 20",
                "petko@abv.bg", new Date(), null, true);

        Diagnose alcoholism = new Diagnose("Dangerous drinking", patient);
        alcoholism.getComments().add("This disease is very bad...");
        alcoholism.getComments().add("But it's tasty...");

        Medicament beer = new Medicament("Beer", patient);

        Visitation firstVisitation = new Visitation(new Date(), patient);
        firstVisitation.getComments().add("Petko was drunk...");

        patient.getDiagnoses().add(alcoholism);
        patient.getMedicines().add(beer);
        patient.getVisitations().add(firstVisitation);

        entityManager.persist(patient);
        entityManager.persist(alcoholism);
        entityManager.persist(beer);
        entityManager.persist(firstVisitation);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
