package com.bootcamp.nttdata.repository;

import com.bootcamp.nttdata.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Mono<Customer> findByDocumentNumber(Integer documentNumber);
}
