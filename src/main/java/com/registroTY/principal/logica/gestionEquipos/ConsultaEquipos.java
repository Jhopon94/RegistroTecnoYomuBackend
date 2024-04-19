/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionEquipos;

import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import java.util.List;

/**
 *
 * @author Jhopon
 */
public class ConsultaEquipos {
    
    String estadoEquipo;
    EquipoServicioInterfaz servicioEquipo;
    
    public ConsultaEquipos(String estadoEquipo, EquipoServicioInterfaz servicioEquipo){
    
        this.estadoEquipo = estadoEquipo;
        this.servicioEquipo = servicioEquipo;
    }
    
    public List<Equipo> ListaEquipos(){
    
        return switch (estadoEquipo) {
            case "ingresados" -> servicioEquipo.ListaEquiposIngresados();
            case "entregados" -> servicioEquipo.ListaEquiposEntregados();
            default -> null;
        };
    }
}
