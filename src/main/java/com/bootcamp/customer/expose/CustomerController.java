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
@RequestMapping(value = "/api/customers")
@RestController
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@RequestBody Customer customer) {
        log.info("<<<<< Create Customer >>>>>");
        return customerService.create(customer);
    }

    @GetMapping("/{id}")
    public Mono<Customer> findById(@PathVariable String id) {
        log.info("<<<<< Find One Customer >>>>>");
        return customerService.findById(id);
    }

    @GetMapping("")
    public Flux<Customer> findAll(@RequestParam(value = "dni",defaultValue = "") String dni) {
        log.info("<<<<< Find All Customers >>>>>");
        //return customerService.findAll();
        return dni.isEmpty()?customerService.findAll():customerService.findByDni(dni);
    }

    @PutMapping("")
    public Mono<ResponseEntity<Customer>> update(@RequestBody Customer customer) {
        log.info("<<<<< Update Customer >>>>>");
        return customerService.update(customer)
                .flatMap(customerUpdate -> Mono.just(ResponseEntity.ok(customerUpdate)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<Customer> delete(@PathVariable String id) {
        log.info("<<<<< Delete Customer >>>>>");
        return customerService.delete(id);
    }

}
