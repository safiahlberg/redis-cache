package com.wixia.rediscache.persistence;

import java.util.List;

public interface CustomerRepositorySlow {

    Iterable<CustomerEo> findAllDelayable(long delayInMs);


    List<CustomerEo> findByFirstNameAndLastNameDelayable(String firstName, String lastName, long delayInMs);

}
