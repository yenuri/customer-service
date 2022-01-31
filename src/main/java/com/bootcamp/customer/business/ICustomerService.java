package com.bootcamp.customer.business;

import com.bootcamp.customer.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Mono<Customer> create(Customer customer);

    Mono<Customer> findById(String id);

    Flux<Customer> findAll();

    Mono<Customer> update(Customer customer);

    Mono<Customer> delete(String id);

    Flux<Customer> findByDni(String dni);
}
