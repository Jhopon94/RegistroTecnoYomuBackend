/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionContable;

import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultarDeudores {

   private EquipoServicioInterfaz servicioEquipo;
   private DetallesServicioInterfaz servicioDetalles;

   private List<Map<String, Object>> deudoresLista;
   private List<Map<String, Object>> datosDeudores;

   public ConsultarDeudores(EquipoServicioInterfaz servicioEquipo, DetallesServicioInterfaz servicioDetalles) {

      this.servicioEquipo = servicioEquipo;
      this.servicioDetalles = servicioDetalles;
      deudoresLista = new ArrayList<>();
      //Se obtiene cedula y nombre de los deudores, pero crudos y repetidos.
      datosDeudores = servicioEquipo.ObtenerDatosDeudores();
      //se quitan los datos repetidos de la lista
      datosDeudores = BorrarDatosRepetidos(datosDeudores);
   }

   public List<Map<String, Object>> ListaDeudoresFull() {
      
      //Por cada deudor
      for(Map<String, Object> deudor : datosDeudores){
         //Se obtienen los equipos adeudados
         List<Map<String, Object>> equipos = servicioEquipo.EquiposAdeudados();
         //Se crea una lista nueva para evitar probelmas de añadir objetos a una lista mientras se recorre
         List<Map<String, Object>> equiposNuevaLista = new ArrayList<>();
         //Por cada equipo adeudado
         for(Map<String, Object> equipo : equipos){
            String idEquipo = equipo.get("id").toString();
            List<Map<String, Object>> detalles = servicioDetalles.DetallesEquipoAdeudado(idEquipo);
            //Creamos una copia del equipo con detalles (porque no se pued modficar con put un objeto iterado
            Map<String, Object> equipoConDetalles = new HashMap<>(equipo);
            //Añadimos la lista de detalles al objeto equipo
            equipoConDetalles.put("detalles", detalles);
            //Añadimos el equipo a la lista de equipos
            equiposNuevaLista.add(equipoConDetalles);
         }
         //Copia del objeto deudor, pero ahora con equipos
         Map<String, Object> deudorConEquipos = new HashMap<>(deudor);
         //Se añade la lista de equipos al objeto deudor
         deudorConEquipos.put("equipos", equiposNuevaLista);
         //Se añade el deudor a la lsita de deudores
         deudoresLista.add(deudorConEquipos);
      }return deudoresLista;
   }

   private List<Map<String, Object>> BorrarDatosRepetidos(List<Map<String, Object>> lista) {

      List<Map<String, Object>> ListaSinRepeticion = new ArrayList<>();
      List<String> id = new ArrayList<>();

      for (Map<String, Object> objeto : lista) {
         //Si es el primer registro
         if (id.isEmpty()) {
            ListaSinRepeticion.add(objeto);
            //añado la cedula a la lista de cedulas para tener registro y poder comparar después
            id.add(objeto.get("id").toString());
         } else {
            String idObjeto = objeto.get("id").toString();
            //Si la lista NO contiene la cadena de texto
            if (!id.contains(idObjeto)) {
               ListaSinRepeticion.add(objeto);
               //añado la cedula a la lista de cedulas para tener registro y poder comparar después
               id.add(objeto.get("id").toString());
            }
         }
      }
      return ListaSinRepeticion;
   }
}


