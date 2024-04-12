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
public class Empleado {
    
    private int id;
    private String nombre;
    private String celular;
    private String correo;
    private String direccion;
    private Date fechaRegistro;
    private String cargo;
    private String foto;
    private boolean disponibleParaUsuario;
}
