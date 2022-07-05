package com.wixia.rediscache.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseExampleDataLoader {

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, ItemRepository itemRepository) {
        return args -> {
            CustomerEo frodo = customerRepository.save(new CustomerEo("Frodo", "Baggins"));
            CustomerEo bilbo = customerRepository.save(new CustomerEo("Bilbo", "Baggins"));
            CustomerEo samwise = customerRepository.save(new CustomerEo("Samwise", "Gamgee"));
            customerRepository.save(new CustomerEo("Peregrin", "Took"));
            customerRepository.save(new CustomerEo("Meriadoc", "Brandybuck"));

            itemRepository.save(new ItemEo("Lembas", 1, samwise));
            itemRepository.save(new ItemEo("Mithril Shirt", 10000000, frodo));
            itemRepository.save(new ItemEo("The One Ring", 1000000000, bilbo));
        };
    }
}
