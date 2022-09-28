package com.wixia.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findByName(String name);

    @Query("select i from Item i where i.price >= :lowerBound and i.price <= :upperBound")
    List<Item> findInRange(int lowerBound, int upperBound);
}
