/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Empleado;
import com.registroTY.principal.repository.EmpleadoRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoServicio implements EmpleadoServicioInterfaz {
    
    @Autowired
    private EmpleadoRepo repoEmpleado;
    
    @Override
    public List<Empleado> ListaEmpleados(){
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<Empleado> listaEmpleados = (List<Empleado>) repoEmpleado.findAll();
        return listaEmpleados;
    }

    @Override
    public void GuardarEmpleado(Empleado empleado) {
        
        repoEmpleado.save(empleado);
    }
}
