package com.wixia.rediscache.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositorySlow {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEo> findAllDelayable(long delayInMs) {

           delay(delayInMs);

        return entityManager.createQuery("SELECT c FROM customer c").getResultList();
    }

    @Override
    public List<CustomerEo> findByFirstNameAndLastNameDelayable(String firstName, String lastName, long delayInMs) {
        delay(delayInMs);

        return entityManager.createQuery(
                "SELECT c FROM customer c WHERE c.firstName = :searchName or c.lastName = :searchName")
                .getResultList();
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
