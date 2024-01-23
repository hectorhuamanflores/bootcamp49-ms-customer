package com.bootcamp.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(value = "CUSTOMERS")
public class Customer {

    @Id
    private String id;              //Identificador Cliente.
    private String name;       //Nombre
    private String tyCustomer;      //Persona - Empresarial
    private Integer documentNumber;  //Dni - Ruc
	

}
