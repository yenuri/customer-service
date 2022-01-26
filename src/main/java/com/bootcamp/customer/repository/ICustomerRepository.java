package com.bootcamp.customer.repository;

import com.bootcamp.customer.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
