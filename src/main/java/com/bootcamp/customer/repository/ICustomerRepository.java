package com.bootcamp.customer.repository;

import com.bootcamp.customer.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ICustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Flux<Customer> findByDni(String dni);
}
