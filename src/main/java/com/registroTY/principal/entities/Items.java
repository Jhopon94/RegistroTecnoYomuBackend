/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;

import jakarta.persistence.Column;
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
@Table(name = "items")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Items {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message="Debe especificarse un nombre de ítem!")
    @Column(unique = true) //estblecer unico a nivel de backend también
    private String nombre;
    @NotBlank(message="Debe especificarse el tipo de ítem!")
    private String tipoItem;
    @NotBlank(message="Debe especificarse una descripción!")
    private String descripcion;
    @NotNull(message="Debe calcularse el saldo!")
    private int saldo;
    private Date fechaCreacion;
}
