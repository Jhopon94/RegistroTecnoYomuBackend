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
        System.out.println("Guardando empleado... con disponibilidad: " + empleado.getDisponibleParaUsuario());
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
    
    @Override
    public String DesactivarEmpleado(int id){
       System.out.println("Vamos a desactivar el empleado...");
       try {
          repoEmpleado.MarcarInactivo(id);
          return "Empleado desactivado satisfactoriamente!";
       } catch (Exception e) {
          System.out.println("Error al desactivar empleado por...");
          return "Error al desactivar empleado!";
       }
    }
    
    @Override
    public boolean ConsultarExistencia(int id){
       System.out.println("Consultando si el empleado existe por su cédula...");
       try {
          repoEmpleado.findById(id);
          return true;
       } catch (Exception e) {
          System.out.println("No se encontró el empleado!");
          return false;
       }
    }
    
    @Override
    public Empleado ObtenerEmpleado(int id){
       System.out.println("Obteniendo objeto empleado...");
       try {
          Empleado empleadoAux = new Empleado();
          empleadoAux = repoEmpleado.findById(id).get();
          if(empleadoAux instanceof Empleado) return empleadoAux;
          else return null;
       } catch (Exception e) {
          System.out.println("Error al obtener el empleado por: " + e);
          return null;
       }
    }
}
