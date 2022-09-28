package com.wixia.controller;

import com.wixia.domain.Customer;
import com.wixia.model.CustomerRepresentationModelAssembler;
import com.wixia.service.CustomerService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * A tight coupling to between controller and repository, but OK for demonstration.
 */
@RestController
public class CustomerController {

    private final CustomerService service;
    private final CustomerRepresentationModelAssembler assembler;

    public CustomerController(CustomerService customerService, CustomerRepresentationModelAssembler customerRepresentationModelAssembler) {
        this.service = customerService;
        this.assembler = customerRepresentationModelAssembler;
    }

    // GET

    @GetMapping("/customers")
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> getAll() {
        return ResponseEntity.ok(this.assembler.toCollectionModel(service.findAll()));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<EntityModel<Customer>> getOne(@PathVariable long id) {
        return this.service.findById(id)
            .map(this.assembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/customers", params = {"firstName"})
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> getByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(
            this.assembler.toCollectionModel(
                service.findByFirstName(firstName)));
    }

    @GetMapping(value = "/customers", params = {"lastName"})
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> getByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(
            this.assembler.toCollectionModel(
                service.findByLastName(lastName)));
    }

    @GetMapping(value = "/customers", params = {"firstName", "lastName"})
    public ResponseEntity<EntityModel<Customer>> getOneByFirstNameAndLastName(
        @RequestParam String firstName, @RequestParam String lastName) {
        return service.findByFirstNameAndLastName(firstName, lastName)
            .map(this.assembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // POST

    @PostMapping("/customers")
    public ResponseEntity<?> post(@RequestBody Customer customerFromRequest) {
        final Customer savedCustomer = service.save(customerFromRequest);

        return ResponseEntity.ok(
            this.assembler.toModel(savedCustomer)
        );
    }

    // PUT

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> put(@PathVariable("id") long id, @RequestBody Customer customerFromRequest) {
        service.findById(id).orElseThrow(() -> {
            throw new RuntimeException(String.format("Could not find customer with ID: %d", id));
        });

        final Customer updatedCustomer = new Customer(customerFromRequest, id);
        final Customer savedCustomer = service.save(updatedCustomer);

        return ResponseEntity.ok(
            this.assembler.toModel(savedCustomer)
        );
    }
}
