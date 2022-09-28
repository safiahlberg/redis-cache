package com.wixia.model;

import com.wixia.controller.ItemController;
import com.wixia.domain.Item;

import org.springframework.hateoas.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ItemRepresentationModelAssembler extends
    SimpleIdentifiableRepresentationModelAssembler<Item> {

    /**
     * Link the {@link com.wixia.domain.Item} domain type to the {@link ItemController} using this
     * {@link SimpleIdentifiableRepresentationModelAssembler} in order to generate both
     * {@link org.springframework.hateoas.EntityModel} and {@link org.springframework.hateoas.CollectionModel}
     */
    public ItemRepresentationModelAssembler() {
        super(ItemController.class);
    }
}
