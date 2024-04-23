/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionContable;

import com.registroTY.principal.entities.Cliente;
import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.entities.Ingreso;
import com.registroTY.principal.services.ClienteServicioInterfaz;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsultaIngresos {

   DetallesServicioInterfaz servicioDetalles;
   EquipoServicioInterfaz servicioEquipo;
   ClienteServicioInterfaz servicioClientes;
   LocalDate fechaInicio;
   LocalDate fechaFin;
   List<Ingreso> listaFinalIngresos;

   public ConsultaIngresos(LocalDate fechaInicio, LocalDate fechaFin, DetallesServicioInterfaz servicioDetalles, EquipoServicioInterfaz servicioEquipo, ClienteServicioInterfaz servicioClientes) {
      this.servicioEquipo = servicioEquipo;
      this.servicioDetalles = servicioDetalles;
      this.servicioClientes = servicioClientes;
      this.fechaInicio = fechaInicio;
      this.fechaFin = fechaFin;
      listaFinalIngresos = new ArrayList<>();
   }

   public List<Ingreso> listaIngresos() {

      try {
         //Obtener los equipos entregados, es decir, los que ya pagaron.
         List<Equipo> listaEquipos = servicioEquipo.ListaEquipoRangoPagaron(fechaInicio, fechaFin);

         //Lista de los ids de los equipos
         List<String> listaIDSEquipos = new ArrayList<>();
         if (listaEquipos != null && !listaEquipos.isEmpty()) {

            for (Equipo equipo : listaEquipos) {
               listaIDSEquipos.add(equipo.getId());
            }
         }

         //Lista idCliente sin repetir
         List<Integer> listaIDSClientes = new ArrayList<>();
         //Lista de clientes sin repetir
         List<Cliente> listaClientesUnicos = new ArrayList<>();

         if (listaEquipos != null && !listaEquipos.isEmpty()) {
            //Se hace el primer registro para no estar evaluando en cada ciclo si la lsita está vacía
            listaIDSClientes.add(listaEquipos.get(0).getIdCliente());
            for (Equipo equipo : listaEquipos) {
               int id = equipo.getIdCliente();
               //Si no existe el registro
               if (!listaIDSClientes.contains(id)) {
                  listaIDSClientes.add(id);
               }
            }
            //Se obtiene la lsita de clientes sin repetir
            listaClientesUnicos = servicioClientes.EncontrarClientesPorIDS(listaIDSClientes);
            if (listaClientesUnicos != null && !listaClientesUnicos.isEmpty()) {
               List<Detalles> listaDetallesRangoIDEquipo = servicioDetalles.ListaDetallesIDEquipo(fechaInicio, fechaFin, listaIDSEquipos);
               if (listaDetallesRangoIDEquipo != null && !listaDetallesRangoIDEquipo.isEmpty()) {
                  for (Cliente cliente : listaClientesUnicos) {
                     //Empiezo a crear el objeto ingreso para agregarlo a la lista
                     Ingreso ingresoAux = new Ingreso();
                     ingresoAux.setCedulaCliente(cliente.getId());
                     ingresoAux.setNombreCliente(cliente.getNombre());
                     //Obtenemos los detalles que le pertenecen al cliente
                     List<Detalles> detallesAuxiliar = new ArrayList<>();
                     //Obtenemos el id del equipo
                     for (Equipo equipo : listaEquipos) {
                        if (cliente.getId().intValue() == equipo.getIdCliente().intValue()) {
                           String idEquipo = "";
                           idEquipo = equipo.getId();
                           //Ahora agregamos los detalles al auxiliar de detalles
                           for (Detalles detalle : listaDetallesRangoIDEquipo) {
                              if (detalle.getIdEquipo().equals(idEquipo)) {
                                 detallesAuxiliar.add(detalle);
                              }
                           }

                        }
                     }

                     //Agregamos la lsita de detalles al ingreso
                     ingresoAux.setListaDetalles(detallesAuxiliar);
                     //Agregamos el ingreso a la lsita de ingresos
                     listaFinalIngresos.add(ingresoAux);
                  }
                  return listaFinalIngresos;
               } else {
                  System.out.println("Error o lista vacía al encontrar los detalles con el rango de fecha y el id del equipo");
                  return null;
               }
            } else {
               System.out.println("Error olista vacía de clientes únicos");
               return null;
            }
         } else {
            return null;
         }

      } catch (Exception e) {
         System.out.println("Error al obtener ingresos...");
         return null;
      }

   }

}
