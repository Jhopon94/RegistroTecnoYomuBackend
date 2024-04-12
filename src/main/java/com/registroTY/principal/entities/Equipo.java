/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode
@ToString
public class Equipo {
    
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
