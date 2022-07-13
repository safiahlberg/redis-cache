package com.wixia.rediscache.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * To simulate delay, in order to test cache effects.
 */
public class CustomerRepositoryImpl implements CustomerRepositoryDelayable {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEo> findAllDelayable(long delayInMs) {

        delay(delayInMs);

        return entityManager.createQuery("select c from customer c").getResultList();
    }

    @Override
    public CustomerEo findByFirstNameAndLastNameDelayable(String firstName, String lastName, long delayInMs) {

        delay(delayInMs);

        return (CustomerEo) entityManager.createQuery(
                "select c from customer c where c.firstName = :firstName and c.lastName = :lastName")
            .setParameter("firstName", firstName)
            .setParameter("lastName", lastName)
            .getSingleResult();
    }

    private void delay(long delayInMs) {
        if (delayInMs > 0) {
            try {
                Thread.sleep(delayInMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
