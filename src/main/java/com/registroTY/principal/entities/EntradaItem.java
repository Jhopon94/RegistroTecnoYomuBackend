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
public class EntradaItem {
    
    private String id;
    private String idItem;
    private int cantidad;
    private int precioTotal;
    private int costoUnitario;
    private Date fechaCompra;
}
