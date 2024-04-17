/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Table(name = "cliente")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Cliente {
    
    @Id
    @NotNull(message="Debe especificarse un id!")
    @Positive
    private Integer id;
    @NotBlank(message="Debe especificarse un nombre!")
    @Column(unique = true)
    private String nombre;
    @NotBlank(message="Debe especificarse un número de celular!")
    private String celular;
    @Email
    @Column(unique = true)
    private String correo;
    private String direccion;
    private Date fechaRegistro;
    @NotNull
    @PositiveOrZero
    private Integer serviciosTomados;
}
