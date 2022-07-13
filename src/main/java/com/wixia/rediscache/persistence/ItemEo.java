package com.wixia.rediscache.persistence;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "item")
public class ItemEo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEo owner;

    protected ItemEo() {}

    public ItemEo(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public ItemEo(String name, Integer price, CustomerEo owner) {
        this.name = name;
        this.price = price;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CustomerEo getOwner() {
        return owner;
    }

    public void setOwner(CustomerEo owner) {
        this.owner = owner;
    }

/*
    @Override
    public String toString() {
        return String.format(
            "Item[id=%d, name='%s', price='%d', owner='%s']",
            id, name, price, Optional.of(owner).map(CustomerEo::toString));
    }
*/

}
