package com.bootcamp.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.customer.entity.CustomerByNumDocRequest;
import com.bootcamp.customer.model.Customer;
import com.bootcamp.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Customer")
public class CustomerController {

	private final CustomerService customerService;

    @GetMapping
    public Mono<ResponseEntity<Flux<Customer>>>getAllCustomer() {
        Flux<Customer> list=this.customerService.getAllCustomer();
        return  Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list));
    }

    @PostMapping("/numberDocument")
    public Mono<ResponseEntity<Customer>> getCustomerByNumDoc(@RequestBody CustomerByNumDocRequest customerByNumDocRequest){
        var customer=this.customerService.getCustomerByDocumentNumber(customerByNumDocRequest.getDocumentNumber());
        return customer.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@RequestBody Customer customer){
        return this.customerService.createCustomer(customer);
    }

    @PutMapping("/updateCustomer")
    public Mono<ResponseEntity<Customer>> updateCustomer(@RequestBody Customer customer){

        return this.customerService.updateCustomer(customer)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomerById(@PathVariable String id){
        return this.customerService.deleteCustomer(id)
                .map(r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
