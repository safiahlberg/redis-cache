package com.wixia.controller;

import com.wixia.domain.Item;
import com.wixia.model.ItemRepresentationModelAssembler;
import com.wixia.service.ItemService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    private final ItemService service;

    private final ItemRepresentationModelAssembler assembler;

    public ItemController(ItemService itemService, ItemRepresentationModelAssembler assembler) {
        this.service = itemService;
        this.assembler = assembler;
    }

    // GET

    @GetMapping("/items")
    public ResponseEntity<CollectionModel<EntityModel<Item>>> getAll() {
        return ResponseEntity.ok(this.assembler.toCollectionModel(service.findAll()));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<EntityModel<Item>> getOne(@PathVariable long id) {
        return this.service.findById(id)
            .map(this.assembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // POST

    @PostMapping("/items")
    public ResponseEntity<?> post(@RequestBody Item itemFromRequest) {
        final Item savedItem = service.save(itemFromRequest);

        return ResponseEntity.ok(
            this.assembler.toModel(savedItem)
        );
    }
}
