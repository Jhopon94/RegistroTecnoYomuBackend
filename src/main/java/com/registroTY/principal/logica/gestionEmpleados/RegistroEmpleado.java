/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionEmpleados;

import com.registroTY.principal.entities.Empleado;
import com.registroTY.principal.services.EmpleadoServicioInterfaz;


public class RegistroEmpleado {
    
        
    private Empleado empleado;
    private EmpleadoServicioInterfaz servicioEmpleado;
    private String mensaje;
    
    public RegistroEmpleado(Empleado empleado, EmpleadoServicioInterfaz servicioEmpleado){
        this.empleado = empleado;
        this.servicioEmpleado = servicioEmpleado;
    }
    
    
    public String RegistrarEmpleado(){
        
        //Primero checamos que no exista
        if(!EmpleadoExiste()){
            empleado.setActivo(true); //Se marca como activo cuando se registra el empleado por primera vez
            empleado.setDisponibleParaUsuario(true);//se marca activo también
            return servicioEmpleado.GuardarEmpleado(empleado);
        }
            
        else return mensaje;
    }
    
    //Para notificar al usuario en caso de que el cliente exista en la base de datos
    private boolean EmpleadoExiste(){
        mensaje = servicioEmpleado.ConsultarEmpleado(empleado);
        //si cliente no existe, es decir, se devolvió el nombre
        return !empleado.getNombre().equals(mensaje);
    }
}
