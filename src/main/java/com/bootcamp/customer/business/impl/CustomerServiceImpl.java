package com.bootcamp.customer.business.impl;

import com.bootcamp.customer.business.ICustomerService;
import com.bootcamp.customer.model.Customer;
import com.bootcamp.customer.repository.ICustomerRepository;
import com.bootcamp.customer.utils.CustomerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public Mono<Customer> create(Customer customer) {

        customer.setStatus(CustomerStatus.REGISTERED.name());
        customer.setCreationDateAt(Calendar.getInstance().getTime());
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> findById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll()
                .filter(p -> p.getStatus().equals(CustomerStatus.REGISTERED.name()));
    }

    @Override
    public Mono<Customer> update(Customer customer) {

        return customerRepository.findById(customer.getId())
                .filter(p -> p.getStatus().equals(CustomerStatus.REGISTERED.name()))
                .switchIfEmpty(Mono.empty())
                .flatMap(customerDB -> customerRepository.save(customer));
    }

    @Override
    public Mono delete(String id) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .doOnNext(p -> p.setStatus(CustomerStatus.DELETED.name()))
                .flatMap(customerRepository::save);
    }

    @Override
    public Flux<Customer> findByDni(String dni) {
        return customerRepository.findByDni(dni)
                .switchIfEmpty(Mono.empty())
                .filter(p -> p.getStatus().equals(CustomerStatus.REGISTERED.name()));
    }
}
