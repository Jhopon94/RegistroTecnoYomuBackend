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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Usuario {
    
    @Id
    @NotNull(message="Debe especificarse el id del empleado usado!")
    @Positive
    private Integer idEmpleado;
    @NotBlank(message="Debe especificarse un nombre de usuario!")
    @Pattern(regexp = "\\S+", message = "El nombre de uusario no debe llevar espacios") //No puede tener espacios
    private String nombreUsuario;
    @NotBlank(message="Debe especificarse una contraseña!")
    private String contrasenia;
    @NotBlank(message="Debe especificarse un rol!")
    private String rol;
    private Date fechaRegistro;
}