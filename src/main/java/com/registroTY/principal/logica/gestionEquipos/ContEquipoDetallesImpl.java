/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionEquipos;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Equipo;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;

@Getter
public class ContEquipoDetallesImpl{
    
    /* Se usan estas anotaciones valid, para cuando el método post
    valide el objeto ContEquipoDetallesImpl valide también estas entidades*/
    @Valid
    private Equipo equipo;
    @Valid
    private List<Detalles> detalles;
    
}
