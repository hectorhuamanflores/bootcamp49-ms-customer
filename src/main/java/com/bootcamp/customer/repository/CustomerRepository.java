package com.bootcamp.customer.repository;

import com.bootcamp.customer.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends ReactiveCrudRepository<Customer,String> {
    /*
     * find(loQuetrae)By(loQueBusca)
     * findByNombreContainingOrApellidoContaining(String nombre,String apellido);f
     * 
     */
	Mono<Customer> findByDocumentNumber(Integer documentNumber);
	
}
