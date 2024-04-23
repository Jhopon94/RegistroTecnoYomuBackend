/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.Empleado;
import java.util.List;
import java.util.Optional;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface EmpleadoServicioInterfaz {
    
    List<Empleado> ListaEmpleados();
    
    String GuardarEmpleado(Empleado empleado);
    
    public String ConsultarEmpleado(Empleado empleado);
    
    String DesactivarEmpleado(int id);
    
    boolean ConsultarExistencia(int id);
    
    Empleado ObtenerEmpleado(int id);
}
