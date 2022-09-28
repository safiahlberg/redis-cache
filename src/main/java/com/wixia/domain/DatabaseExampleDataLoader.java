package com.wixia.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseExampleDataLoader {

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, ItemRepository itemRepository) {
        return args -> {
            final Customer frodo = customerRepository.save(new Customer("Frodo", "Baggins"));
            final Customer bilbo = customerRepository.save(new Customer("Bilbo", "Baggins"));
            final Customer sam = customerRepository.save(new Customer("Samwise", "Gamgee"));
            customerRepository.save(new Customer("Peregrin", "Took"));
            customerRepository.save(new Customer("Meriadoc", "Brandybuck"));

            itemRepository.save(new Item("Lembas", 1, sam));
            itemRepository.save(new Item("Mithril Shirt", 10000000, bilbo));
            itemRepository.save(new Item("Sting", 500000, bilbo));
            itemRepository.save(new Item("The One Ring", 1000000000, frodo));
        };
    }
}
