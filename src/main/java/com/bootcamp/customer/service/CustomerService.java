package com.bootcamp.customer.service;

import com.bootcamp.customer.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    public Flux<Customer> getAllCustomer();
    public Mono<Customer> getCustomerById(String id);
    public Mono<Customer> createCustomer(Customer customer);
    public Mono<Customer> updateCustomer(Customer customer);
    public Mono<Customer> deleteCustomer(String id);
    public Mono<Customer> getCustomerByDocumentNumber(Integer numDoc);
    

}
