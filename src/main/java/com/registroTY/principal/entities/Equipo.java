
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "equipo")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Equipo {
    
    @Id
    private String id;
    
    @NotNull(message="Debe especificarse el id del cliente!")
    @Positive
    private Integer idCliente;
    
    @NotBlank(message="Debe especificarse el tipo de ingreso!")
    private String tipoIngreso;
    
    @NotBlank
    private String modelo;
    
    @NotBlank(message="Debe especificarse las condiciones físicas en que se recibe!")
    private String condicionesFisicasRecibidas;
    
    private String daniosRecibido;
    
    @NotBlank(message="Debe especificarse las aprtes internas con que ingresa!")
    private String partesInternasRecibido;
    
    @NotNull(message="Debe calcularse el total!")
    @PositiveOrZero
    private Integer precioTotal;
    
    @NotBlank(message="Debe especificarse el estado del equipo!")
    private String estadoEquipo;
    
    @NotNull(message="Debe especificarse el saldo pendiente!")
    @PositiveOrZero
    private Integer saldoPendiente;
    
    private Date fechaIngreso;
    
    private Date fechaSalida;
    
    @NotNull
    @PositiveOrZero
    private Integer diasGarantia;
    
}
