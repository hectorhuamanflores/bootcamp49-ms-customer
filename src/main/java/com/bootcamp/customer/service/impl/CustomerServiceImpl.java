package com.bootcamp.customer.service.impl;

import org.springframework.stereotype.Service;

import com.bootcamp.customer.model.Customer;
import com.bootcamp.customer.repository.CustomerRepository;
import com.bootcamp.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	private  final CustomerRepository customerRepository;

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
    	if(customer !=null) {
    		log.error("INICIO_CREACION_CUSTOMER");
    		log.info("name: "+customer.getName());
    		log.info("tyCustomer: "+customer.getTyCustomer());
    		log.info("documentNumber: "+customer.getDocumentNumber());
    		return customerRepository.save(customer);
    	}else {
    		log.error("Customer is null");
    		throw new RuntimeException("Customer is null");
    	}
       
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer) {
    	
        
        return customerRepository.findByDocumentNumber(customer.getDocumentNumber())
                .flatMap( objeto ->{
                    objeto.setName(customer.getName());
                    objeto.setDocumentNumber(customer.getDocumentNumber());
                    objeto.setTyCustomer(customer.getTyCustomer());
                    return customerRepository.save(objeto);
                 });
    }

    @Override
    public Mono<Customer> deleteCustomer(String id) {
        return customerRepository.findById(id)
                .flatMap(existsCustomerRepository -> customerRepository.delete(existsCustomerRepository)
                        .then(Mono.just(existsCustomerRepository)));
    }

	@Override
	public Mono<Customer> getCustomerByDocumentNumber(Integer numDoc) {
		log.error("INICIO_CUSTOMERBYDOCUMENT");
		log.info("numDoc: "+numDoc);
		return customerRepository.findByDocumentNumber(numDoc);
	}
}
