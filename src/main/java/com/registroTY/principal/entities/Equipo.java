/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int idCliente;
    private String tipoIngreso;
    private String condicionesFisicasRecibidas;
    private String daniosRecibido;
    private Date partesInternasRecibido;
    private int precioTotal;
    private String estadoEquipo;
    private int saldoPendiente;
    private Date fechaIngreso;
    private Date fechaSalida;
    private int diasGarantia;
    
}
