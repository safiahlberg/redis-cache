package com.wixia.rediscache.persistence;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Supports cacheing and also the possibility to simulate delays, in order so see cache effects.
 */
public interface CustomerRepositoryDelayable {

    /**
     * Find all customers with a possible delay.
     *
     * @param delayInMs simulated delay, in order to see cache effects.
     * @return the customers
     */
    List<CustomerEo> findAllDelayable(long delayInMs);

    /**
     * Find all customers with given first and last name, with a possible delay.
     *
     * @param delayInMs simulated delay, in order to see cache effects.
     * @return the customers
     */
    @Cacheable(value = "customerCache", key = "{#firstName, #lastName}")
    CustomerEo findByFirstNameAndLastNameDelayable(String firstName, String lastName, long delayInMs);

}
