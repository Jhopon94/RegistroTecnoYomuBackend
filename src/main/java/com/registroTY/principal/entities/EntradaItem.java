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
import org.hibernate.annotations.Immutable;

@Immutable //Para que no s epueda modificar una vez creado
@Entity
@Table(name = "entradaItem")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class EntradaItem {
    
    @Id
    private int id;
    private int idItem;
    private int cantidad;
    private int precioTotal;
    private int costoUnitario;
    private Date fechaCompra;
}
