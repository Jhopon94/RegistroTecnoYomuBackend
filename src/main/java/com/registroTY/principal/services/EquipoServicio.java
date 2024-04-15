/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.repository.EquipoRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EquipoServicio implements EquipoServicioInterfaz {
    
    @Autowired
    private EquipoRepo repoEquipo;
    
    @Override
    public List<Equipo> ListaEquipos(){
        
        System.out.println("Consultando lista de Equipos...");
        try {
        List<Equipo> listaEquipos = (List<Equipo>) repoEquipo.findAll();
        return listaEquipos;
        } catch (Exception e) {
            System.out.println("Error al obtener lista de equipos por: " + e);
            return null;
        }
    }

    @Override
    public void EliminarEquipo(int id) {
        
        System.out.println("Eliminando equipo...");
        try {
        repoEquipo.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar equipo por: " + e);
        }
    }

    @Override
    public void GuardarEquipo(Equipo equipo) {
        
        System.out.println("Guardando equipo...");
        try {
        repoEquipo.save(equipo);
        } catch (Exception e) {
            System.out.println("Error al guardar equipo por: " + e);
        }
    }
}
