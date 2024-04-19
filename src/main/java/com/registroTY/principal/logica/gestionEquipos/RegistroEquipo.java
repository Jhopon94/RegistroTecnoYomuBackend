/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionEquipos;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.logica.TresUltimos;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jhopon
 */
public class RegistroEquipo {

    private Equipo equipo;
    private List<Detalles> detalles;
    private EquipoServicioInterfaz servicioEquipo;
    private DetallesServicioInterfaz servicioDetalles;
    //Creamos una variable para devolver respuesta con dos valores: string y booelan
    Map<String, Object> resultado;

    ///// Constructor/////////////
    public RegistroEquipo(Equipo equipo, List<Detalles> detalles, EquipoServicioInterfaz servicioEquipo, DetallesServicioInterfaz servicioDetalles) {
        this.detalles = detalles;
        this.equipo = equipo;
        this.servicioEquipo = servicioEquipo;
        this.servicioDetalles = servicioDetalles;
        resultado = new HashMap<>();
    }

    //Deevuelve un objeto que contiene mensaje de proceso y boolean de exito en operación!
    public Map<String, Object> RegistrarEquipo() {
        
        //Establecemos el id
        EstablecerIdEquipo(servicioEquipo.UltimoIDEquipo());
        //Obtenemos los resultados de registrar equipo
        Map<String, Object> resultadoEquipo = servicioEquipo.GuardarEquipo(equipo);
        //Solo se registrarán los detalles si se tuvo éxito registrando el equipo
        if ((boolean) resultadoEquipo.get("procesoExitoso")) {

            //Si se tuvo éxito registrando el equipo
            
            //Establecemos el idForaneo a los detalles
            for(Detalles detalle : detalles){
                detalle.setIdEquipo(equipo.getId());
            }
            //Se registran los detalles y se obtiene el resultado
            Map<String, Object> resultadoDetalles = servicioDetalles.GuardarVariosDetalles(detalles);
            //Verificamos que se haya tenido éxito registrando los detalles.
            if ((boolean) resultadoDetalles.get("procesoExitoso")) {
                resultado.put("mensaje", "Equipo y detalles registrados satisfactoriamente");
                resultado.put("procesoExitoso", true);
            } else {
                resultado.put("mensaje", resultadoDetalles.get("mensaje") + ". Equipo Registrado pero sin detalles, intenta editar los detalles desde el panel de gestión de equipos. ");
                resultado.put("procesoExitoso", false);
            }
        } else {
            resultado.put("mensaje", resultadoEquipo.get("mensaje") + " y sus detalles");
            resultado.put("procesoExitoso", false);
        }

        return resultado;
    }

   private void EstablecerIdEquipo(String id){
   
       if(id != null){
           if(id.equals("")){
               equipo.setId("equipo000");
           }else{
               equipo.setId(new TresUltimos().IdNuevo(id));
           }
       }
   }

}
