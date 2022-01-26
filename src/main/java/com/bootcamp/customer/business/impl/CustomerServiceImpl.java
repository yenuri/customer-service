package com.bootcamp.customer.business.impl;

import com.bootcamp.customer.business.ICustomerService;
import com.bootcamp.customer.model.Customer;
import com.bootcamp.customer.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public Mono<Customer> create(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> update(Customer customer) {
        return customerRepository.findById(customer.getId())
                .switchIfEmpty(Mono.empty())
                .flatMap(customerRepository::save);
    }

    @Override
    public Mono delete(String id) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .doOnNext(p -> p.setActive(Boolean.FALSE))
                .flatMap(customerRepository::save);
    }
}
