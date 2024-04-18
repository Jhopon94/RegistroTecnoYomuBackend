/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Cliente;
import com.registroTY.principal.entities.Empleado;
import com.registroTY.principal.repository.EmpleadoRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoServicio implements EmpleadoServicioInterfaz {
    
    @Autowired
    private EmpleadoRepo repoEmpleado;
    
    @Override
    public List<Empleado> ListaEmpleados(){
        
        System.out.println("Consultando lista de empleados...");
        try {
        List<Empleado> listaEmpleados = (List<Empleado>) repoEmpleado.findAll();
        return listaEmpleados;
        } catch (Exception e) {
            System.out.println("Error al obtener lista de empleados por: " + e);
            return null;
        }
    }

    @Override
    public String GuardarEmpleado(Empleado empleado) {
        System.out.println("Guardando empleado...");
        try {
        repoEmpleado.save(empleado);
        return empleado.getNombre() + " registrado como empleado satisfactoriamente!";
        } catch (Exception e) {
            System.out.println("Error al guardar empleado por : " + e);
            return "Error en la aplicación al intentar registrar el empleado!";
        }
    }
    
    @Override
    public String ConsultarEmpleado(Empleado empleado) {
        System.out.println("Consultando un empleado...");
        try {
            if(repoEmpleado.existsById(empleado.getId())){ //Si el objeto tiene resultado interno
                return "El empleado ya existe!";
            }else{ //Si el objeto encontrado  está vacío (null)
                return empleado.getNombre(); //Se devuelve el nombre para poder registrar el empleado
            }
        } catch (Exception e) {
            System.out.println("No se encontró empleado por error en base de datos : " + e);
                return "No se encontró el empleado por error en aplicación!";
        }
    }
}
