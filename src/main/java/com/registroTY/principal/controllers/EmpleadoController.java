/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Empleado;
import com.registroTY.principal.services.EmpleadoServicioInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpleadoController {
    
    @Autowired
    private EmpleadoServicioInterfaz servicioEmpleado;
    
    @GetMapping("/Empleado")
    public List<Empleado> ListaEmpleados(){
        
        return servicioEmpleado.ListaEmpleados();
    }
    
}
