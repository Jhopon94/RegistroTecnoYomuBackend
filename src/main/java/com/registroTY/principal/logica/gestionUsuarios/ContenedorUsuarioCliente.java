/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionUsuarios;

import com.registroTY.principal.entities.Empleado;
import com.registroTY.principal.entities.Usuario;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class ContenedorUsuarioCliente{
    
    /* Se usan estas anotaciones valid, para cuando el método post
    valide el objeto ContEquipoDetallesImpl valide también estas entidades*/
    @Valid
    private Usuario usuario;
    @Valid
    private Empleado empleado;
    
}
