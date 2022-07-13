package com.wixia.rediscache.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseExampleDataLoader {

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, ItemRepository itemRepository) {
        return args -> {
            final CustomerEo frodo = customerRepository.save(new CustomerEo("Frodo", "Baggins"));
            final CustomerEo bilbo = customerRepository.save(new CustomerEo("Bilbo", "Baggins"));
            final CustomerEo sam = customerRepository.save(new CustomerEo("Samwise", "Gamgee"));
            customerRepository.save(new CustomerEo("Peregrin", "Took"));
            customerRepository.save(new CustomerEo("Meriadoc", "Brandybuck"));

            itemRepository.save(new ItemEo("Lembas", 1, sam));
            itemRepository.save(new ItemEo("Mithril Shirt", 10000000, bilbo));
            itemRepository.save(new ItemEo("Sting", 500000, bilbo));
            itemRepository.save(new ItemEo("The One Ring", 1000000000, frodo));
        };
    }
}
