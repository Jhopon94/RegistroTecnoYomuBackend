/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionContable;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Ingreso;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import java.util.Date;
import java.util.List;


public class ConsultaIngresos {
    
    Ingreso ingreso;
    DetallesServicioInterfaz servicioDetalles;
    Date fechaInicio;
    Date fechaFin;

    public ConsultaIngresos(Date fechaInicio, Date fechaFin, DetallesServicioInterfaz servicioDetalles) {
        this.servicioDetalles = servicioDetalles;
        ingreso = new Ingreso();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    public List<Detalles> listaIngresos(){
        
        if(servicioDetalles.ListaDetallesRango(fechaInicio, fechaFin) != null){
            
            List<Detalles> listaAux = servicioDetalles.ListaDetallesRango(fechaInicio, fechaFin);
            System.out.println("Lista de ingresos obtenida!");
            if(!listaAux.isEmpty()){
                return listaAux;
            }else{
                System.out.println("Se obtuvo lista de ingresos pero está vacía!");
                return listaAux;
            }
        }else{
            System.out.println("Error al obtener ingresos...");
            return null;
        }
    }
}
