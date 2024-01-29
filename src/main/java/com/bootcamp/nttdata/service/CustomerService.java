package com.bootcamp.nttdata.service;

import com.bootcamp.nttdata.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    public Flux<Customer> getAllCustomer();
    public Mono<Customer> getCustomerById(String id);
    public Mono<Customer> createCustomer(Customer customer);
    public Mono<Customer> updateCustomer(Customer customer);
    public Mono<Void> deleteCustomer(String id);
    public Mono<Customer> getCustomerByDocumentNumber(Integer numDoc);
}
