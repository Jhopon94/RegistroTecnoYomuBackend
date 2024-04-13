/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.Equipo;
import java.util.List;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface EquipoServicioInterfaz {
    
    List<Equipo> ListaEquipos();
    
    void EliminarEquipo(int id);
    
    void GuardarEquipo(Equipo equipo);
}