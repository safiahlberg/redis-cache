package com.wixia.model;

import com.wixia.domain.Customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class CustomerModel extends RepresentationModel<CustomerModel> {

    private final Customer customer;

    @JsonCreator
    public CustomerModel(@JsonProperty("customer") Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
