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
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "empleado")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Empleado {
    
    @Id
    @NotNull(message="Debe especificarse un id!")
    @Positive
    private Integer id;
    @NotBlank(message="Debe especificarse un nombre!")
    @Column(unique = true)
    private String nombre;
    @NotBlank(message="Debe especificarse un número de celular!")
    private String celular;
    @NotBlank(message="Debe especificarse un correo electrónico!")
    @Email
    @Column(unique = true)
    private String correo;
    @NotBlank(message="Debe especificarse una dirección!")
    private String direccion;
    private Date fechaRegistro;
    @NotBlank(message="Debe especificarse el cargo!")
    private String cargo;
    @NotBlank(message="Debe especificarse una foto!")
    @Column(unique = true)
    private String foto;
    @NotNull(message="Debe determinarse por lógica su disponibilidad!")
    private Boolean disponibleParaUsuario;
    private Boolean activo;//puede ser null porque esto se agrega con lógica
}
