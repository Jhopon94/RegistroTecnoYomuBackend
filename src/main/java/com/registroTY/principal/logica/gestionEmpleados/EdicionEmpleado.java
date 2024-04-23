/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionEmpleados;

import com.registroTY.principal.entities.Empleado;
import com.registroTY.principal.services.EmpleadoServicioInterfaz;

/**
 *
 * @author Jhopon
 */
public class EdicionEmpleado {
   
   EmpleadoServicioInterfaz servicioEmpleado;
   
   public EdicionEmpleado(EmpleadoServicioInterfaz servicioEmpleado){
   
      this.servicioEmpleado = servicioEmpleado;
   }
   
   public String EditarEmpleado(Empleado empleado){
      int id = empleado.getId();
      boolean activo;
      boolean disponible;
      //Se guarda para modificación solo en caso de que la cédula si exista para evitar que se modifique en el front
      if(servicioEmpleado.ConsultarExistencia(id)){
         try {
            activo = servicioEmpleado.ObtenerEmpleado(id).getActivo();
            disponible = servicioEmpleado.ObtenerEmpleado(id).getDisponibleParaUsuario();
            empleado.setActivo(activo);
            empleado.setDisponibleParaUsuario(disponible);
            return servicioEmpleado.GuardarEmpleado(empleado);
         } catch (Exception e) {
            System.out.println("Edición fallida por: " + e);
            return "No se pudo realizar la edición!";
         }
      } 
      else{
        return "No se puede editar la cédula del empleado!";      
      } 
   }
}
