package com.bootcamp.nttdata.service.impl;

import com.bootcamp.nttdata.model.Customer;
import com.bootcamp.nttdata.repository.CustomerRepository;
import com.bootcamp.nttdata.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Flux<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> createCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {

        return customerRepository.deleteById(id);
    }

    @Override
    public Mono<Customer> getCustomerByDocumentNumber(Integer numDoc) {

        return customerRepository.findByDocumentNumber(numDoc);
    }
}
