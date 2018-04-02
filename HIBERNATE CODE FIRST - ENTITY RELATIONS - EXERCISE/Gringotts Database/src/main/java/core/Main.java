package core;

import entities.WizardDeposits;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gringotts");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        WizardDeposits wizardDeposits = new WizardDeposits("Magi", "Petkova", "I don't have any notes yet!",
                -20, "The prachkata", (short) -41, "deposit_people", new Timestamp(System.currentTimeMillis()),
                BigDecimal.valueOf(125514.1), 12, 1, new Timestamp(System.currentTimeMillis()), true);

        entityManager.getTransaction().begin();

         entityManager.persist(wizardDeposits);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
