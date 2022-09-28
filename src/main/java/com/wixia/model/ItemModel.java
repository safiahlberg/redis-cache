package com.wixia.model;

import com.wixia.domain.Item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class ItemModel extends RepresentationModel<ItemModel> {
    private final Item item;

    @JsonCreator
    public ItemModel(@JsonProperty("item") Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
