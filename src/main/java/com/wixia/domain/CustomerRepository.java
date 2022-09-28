package com.wixia.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFirstName(String firstName);

    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT c FROM Customer c WHERE c.firstName = :searchName or c.lastName = :searchName")
    List<Customer> findByFirstNameOrLastName(@Param("searchName") String searchName);

}
