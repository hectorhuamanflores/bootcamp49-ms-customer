package com.bootcamp.nttdata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "customers")
public class Customer {

  private String id;
  private String firstName;
  private String lastName;
  private String tyCustomer;       //Persona o Empresa
  private Integer documentNumber;  //Dni o Ruc
  private String profile;          //Vip o Pyme
  private LocalDate birthDate;     //Fecha de nacimiento
  private LocalDate creationDate;     //Fecha de creación
  private LocalDate lastUpdateDate;   //Fecha ultima actualización

}
