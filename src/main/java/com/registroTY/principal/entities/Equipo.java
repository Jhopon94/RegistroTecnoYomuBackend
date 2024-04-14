/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message="Debe especificarse el id del cliente!")
    private int idCliente;
    @NotBlank(message="Debe especificarse el tipo de ingreso!")
    private String tipoIngreso;
    @NotBlank(message="Debe especificarse las condiciones físicas en que se recibe!")
    private String condicionesFisicasRecibidas;
    private String daniosRecibido;
    @NotBlank(message="Debe especificarse las aprtes internas con que ingresa!")
    private String partesInternasRecibido;
    @NotNull(message="Debe calcularse el total!")
    private int precioTotal;
    @NotBlank(message="Debe especificarse el estado del equipo!")
    private String estadoEquipo;
    @NotNull(message="Debe especificarse el saldo pendiente!")
    private int saldoPendiente;
    private Date fechaIngreso;
    private Date fechaSalida;
    private int diasGarantia;
    
}
