package com.wixia.rediscache.persistence;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "customer")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "firstName", "lastName" }) })
public class CustomerEo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    // EAGER is needed, or else we get in trouble when re-creating from cache
    private List<ItemEo> items;

    protected CustomerEo() {}

    public CustomerEo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.items = new ArrayList<>();
    }

    public CustomerEo(String firstName, String lastName, @NonNull List<ItemEo> items) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<ItemEo> getItems() {
        return items;
    }

/*
    @Override
    public String toString() {
        return String.format(
            "Customer[id=%d, firstName='%s', lastName='%s', items=['%s']]",
            id, firstName, lastName,
            items.stream().map(ItemEo::toString).collect(Collectors.joining(", ", "[", "]")));
    }
*/

}
