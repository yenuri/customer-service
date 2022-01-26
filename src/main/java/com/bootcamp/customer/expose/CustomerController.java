package com.bootcamp.customer.expose;

import com.bootcamp.customer.business.ICustomerService;
import com.bootcamp.customer.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("api/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@RequestBody Customer customer) {
        log.info("<<<<< Create Customer >>>>>");
        return customerService.create(customer);
    }

    @GetMapping("api/customers/{id}")
    public Mono<Customer> findById(@PathVariable String id) {
        log.info("<<<<< Find One Customer >>>>>");
        return customerService.findById(id);
    }

    @GetMapping("api/customers")
    public Flux<Customer> findAll() {
        log.info("<<<<< Find All Customers >>>>>");
        return customerService.findAll();
    }

    @PutMapping("api/customers/{id}")
    public Mono<ResponseEntity<Customer>> update(@RequestBody Customer customer) {
        log.info("<<<<< Update Customer >>>>>");
        return customerService.update(customer)
                .flatMap(customerUpdate -> Mono.just(ResponseEntity.ok(customerUpdate)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("api/customers/{id}")
    public Mono<Customer> delete(@PathVariable String id) {
        log.info("<<<<< Delete Customer >>>>>");
        return customerService.delete(id);
    }

}
