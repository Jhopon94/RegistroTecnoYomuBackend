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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Immutable //Para que no se pueda modificar una vez creado
@Entity
@Table(name = "entradaItem")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class EntradaItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String idItem;
    @NotNull(message="Debe especificarse la cantidad de items entrantes!")
    @Positive
    private Integer cantidad;
    @NotNull(message="Debe calcularse el total!")
    @PositiveOrZero
    private Integer precioTotal;
    @NotNull(message="Debe especificarse el costo unitario!")
    @PositiveOrZero
    private Integer costoUnitario;
    private Date fechaCompra;
}
