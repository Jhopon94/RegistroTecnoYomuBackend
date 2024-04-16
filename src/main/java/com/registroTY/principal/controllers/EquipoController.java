/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.logica.gestionEquipos.ProcesoRegistroImpl;
import com.registroTY.principal.logica.gestionEquipos.RegistroEquipo;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal del ojeto Equipo////////////////////
@RestController
public class EquipoController {
    
    @Autowired
    private EquipoServicioInterfaz servicioEquipo;
    @Autowired
    private DetallesServicioInterfaz servicioDetalles;
    
    @GetMapping("/Equipos")
    public List<Equipo> ListaEquipos(){
        
        return servicioEquipo.ListaEquipos();
    }
    
    @PostMapping("/Equipos")
    public ProcesoRegistroImpl GuardarEquipo(@RequestBody Object[] contenedorObjetos){
    
        RegistroEquipo registrarEquipo = new RegistroEquipo(contenedorObjetos, servicioEquipo, servicioDetalles);
        return registrarEquipo.RegistrarEquipo();
    }
    
    @DeleteMapping("/Equipos/{id}")
    public void EliminarEquipos(@PathVariable int id){
    
        servicioEquipo.EliminarEquipo(id);
    }
}
