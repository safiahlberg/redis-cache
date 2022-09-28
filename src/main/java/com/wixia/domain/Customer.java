package com.wixia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName", "lastName"})})
public class Customer implements Serializable {

    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "customer_seq")
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    // EAGER is needed, or else we get in trouble when re-creating from cache
    private List<Item> items;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.items = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, @NonNull List<Item> items) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.items = items;
    }

    public Customer(Customer copyFrom, long updateId) {
        this.id = updateId;
        this.firstName = copyFrom.firstName;
        this.lastName = copyFrom.lastName;
        this.items = copyFrom.items;
    }

}
