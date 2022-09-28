package com.wixia.service;

import com.wixia.domain.Customer;
import com.wixia.domain.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Cacheable(value = "customerCache")
    public Optional<Customer> findById(long id) {
        return customerRepository.findById(id);
    }

    @Cacheable(value = "customerCache")
    public List<Customer> findAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Cacheable(value = "customerCache")
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Cacheable(value = "customerCache")
    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @Cacheable(value = "customerCache")
    public Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @CacheEvict(value = "customerCache")
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

}
