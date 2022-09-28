package com.wixia.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item implements Serializable {

    @Id
    @SequenceGenerator(name = "item_seq", sequenceName = "item_sequence", allocationSize = 10)
    @GeneratedValue(strategy = SEQUENCE, generator = "item_seq")
    private Long id;

    private String name;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("items") // JsonIgnoreProperties is necessary to avoid circular resolution,
    // but it is cumbersome if it can't be applied outside of the
    // domain context (if we want a clean hexagonal architecture)
    private Customer owner;

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, Integer price, Customer owner) {
        this.name = name;
        this.price = price;
        this.owner = owner;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }
}
