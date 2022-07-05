package com.wixia.rediscache.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(itemResourceRel = "item", collectionResourceRel = "items", path = "items")
public interface ItemRepository extends CrudRepository<ItemEo, Long> {

    List<ItemEo> findByName(String name);

    @Query("select i from item i where i.price >= :lowerBound and i.price <= :upperBound")
    List<ItemEo> findInRange(int lowerBound, int upperBound);
}
