package core;

import entities.BillingDetail;
import entities.CreditCard;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bills_payment");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User("Penko", "Ivankov", "penko@petko.bg", "dedoviq", null);

        BillingDetail billingDetail = new CreditCard("123123123", user, "special card", (byte) 1, (short) 13);


        user.setBillingDetail(billingDetail);

        entityManager.persist(user);
        entityManager.persist(billingDetail);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
