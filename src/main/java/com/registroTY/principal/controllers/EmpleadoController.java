/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Empleado;
import com.registroTY.principal.logica.gestionEmpleados.EdicionEmpleado;
import com.registroTY.principal.logica.gestionEmpleados.RegistroEmpleado;
import com.registroTY.principal.services.EmpleadoServicioInterfaz;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal del objeto Empleado////////////////////
@RestController
public class EmpleadoController {
    
    @Autowired
    private EmpleadoServicioInterfaz servicioEmpleado;
    
    @GetMapping("/Empleados")
    public List<Empleado> ListaEmpleados(){
        return servicioEmpleado.ListaEmpleados();
    }
    
    @PutMapping("/Empleados")
    public String ActualizarEmpleado(@Valid @RequestBody Empleado empleado, BindingResult resultado){
        if(resultado.hasErrors()) return "Error al editar, revisa bien la información ingresada!";
        else return new EdicionEmpleado(servicioEmpleado).EditarEmpleado(empleado);
    }
    
    @PostMapping("/Empleados")
    public String GuardarEmpleado(@Valid @RequestBody Empleado empleado, BindingResult resultado){
        
        if(resultado.hasErrors()){
            return "Hay error en algún dato!";
        }else{
            RegistroEmpleado registrarEmpleado = new RegistroEmpleado(empleado, servicioEmpleado);
            return registrarEmpleado.RegistrarEmpleado();
        }
    }
    
    @PutMapping("/EmpleadoOff")
    public String DesactivarEmpleado(@RequestBody int id, BindingResult resultado){
       return servicioEmpleado.DesactivarEmpleado(id);
    }
}
