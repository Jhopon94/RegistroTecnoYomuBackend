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
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<Equipo> listaEquipos = (List<Equipo>) repoEquipo.findAll();
        return listaEquipos;
    }
}
