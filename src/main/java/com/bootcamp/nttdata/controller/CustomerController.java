package com.bootcamp.nttdata.controller;

import com.bootcamp.nttdata.model.Customer;
import com.bootcamp.nttdata.model.CustomerByNumDocRequest;
import com.bootcamp.nttdata.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Mono<ResponseEntity<Flux<Customer>>>getAllCustomer() {
        Flux<Customer> list=this.customerService.getAllCustomer();
        return  Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list));
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> findById(@PathVariable("id") String id){
        return customerService.getCustomerById(id)
                .map( e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/numberDocument")
    public Mono<ResponseEntity<Customer>> getCustomerByNumDoc(@RequestBody CustomerByNumDocRequest customerByNumDocRequest){
        var customer=this.customerService.getCustomerByDocumentNumber(customerByNumDocRequest.getDocumentNumber());
        return customer.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Customer>> createCustomer(@RequestBody Customer customer, final ServerHttpRequest req){
        return customerService.createCustomer(customer)
                .map( e -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable("id") String id  ,@RequestBody Customer customer){

        Mono<Customer> bodyCustomer = Mono.just(customer);
        Mono<Customer> bdCustomer  = customerService.getCustomerById(id);

        return bdCustomer.zipWith(bodyCustomer, (bd, body) ->{
                    bd.setId(id);
                    bd.setFirstName(body.getFirstName());
                    bd.setLastName(body.getLastName());
                    bd.setTyCustomer(body.getTyCustomer());
                    bd.setDocumentNumber(body.getDocumentNumber());
                    bd.setProfile(body.getProfile());
                    bd.setBirthDate(body.getBirthDate());
                    bd.setLastUpdateDate(body.getLastUpdateDate());
                    return bd;
                })
                .flatMap( e -> customerService.updateCustomer(e))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomerById(@PathVariable String id){

        return customerService.getCustomerById(id)
                .flatMap( e -> customerService.deleteCustomer(e.getId())
                        .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}
