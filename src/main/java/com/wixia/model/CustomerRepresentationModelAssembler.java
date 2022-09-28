package com.wixia.model;

import com.wixia.controller.CustomerController;
import com.wixia.domain.Customer;

import org.springframework.hateoas.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepresentationModelAssembler extends
    SimpleIdentifiableRepresentationModelAssembler<Customer> {

    /**
     * Link the {@link com.wixia.domain.Customer} domain type to the {@link CustomerController} using this
     * {@link SimpleIdentifiableRepresentationModelAssembler} in order to generate both
     * {@link org.springframework.hateoas.EntityModel} and {@link org.springframework.hateoas.CollectionModel}
     */
    public CustomerRepresentationModelAssembler() {
        super(CustomerController.class);
    }
}
