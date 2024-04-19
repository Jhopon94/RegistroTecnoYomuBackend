/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionContable;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Ingreso;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.annotations.Array;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

public class ConsultaIngresos {

   Ingreso ingreso;
   DetallesServicioInterfaz servicioDetalles;
   LocalDate fechaInicio;
   LocalDate fechaFin;

   public ConsultaIngresos(LocalDate fechaInicio, LocalDate fechaFin, DetallesServicioInterfaz servicioDetalles) {
      this.servicioDetalles = servicioDetalles;
      ingreso = new Ingreso();
      this.fechaInicio = fechaInicio;
      this.fechaFin = fechaFin;
   }

   public List<Ingreso> listaIngresos() {

      try {
         List<Map<String, Object>> listaAux = servicioDetalles.ListaDetallesRango(fechaInicio, fechaFin);
         System.out.println("Lista de ingresos obtenida!");
         if (!listaAux.isEmpty()) {

            return FormatoIngreso(listaAux);
         } else {
            System.out.println("Se obtuvo lista de ingresos pero está vacía!");
            List<Ingreso> listaVacia = new ArrayList<>();
            return listaVacia;
         }
      } catch (Exception e) {
         System.out.println("Error al obtener ingresos...");
         return null;
      }

   }

   private List<Ingreso> FormatoIngreso(List<Map<String, Object>> listaCruda) {
      //Obtengo la lista de fechas sin repetir para las comparaciones
      List<LocalDate> listaFechasUnicas = ListaFechasSinRepetir(listaCruda);
      List<LocalDate> fechasEncontradas = new ArrayList<LocalDate>();
      List<Ingreso> listaIngresos = new ArrayList<>();

      //Itero primero la lista de fechas sin repetir
      for (LocalDate fecha : listaFechasUnicas) {

         //Ahora itero la lista cruda para encontrar las coincidencias
         for (Map<String, Object> objeto : listaCruda) {
            //obtengo la fecha del objeto
            DateTimeFormatter formatearFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime fechaHoraObjeto = LocalDateTime.parse(objeto.get("fechaDetalle").toString(), formatearFecha);
            LocalDate fechaObjeto = fechaHoraObjeto.toLocalDate();
            System.out.println("Compararemos " + fecha + " y " + fechaObjeto);
            if (fecha.isEqual(fechaObjeto)) {
               //Si la lista de ingresos está vacía, no hay ingresos aún
               if (listaIngresos.isEmpty()) {
                  //Se crea el objeto Ingreso
                  Ingreso auxIngreso = new Ingreso();
                  auxIngreso.setFecha(fechaObjeto);
                  auxIngreso.setNombreCliente(objeto.get("nombreCliente").toString());
                  auxIngreso.setCedulaCliente(Integer.parseInt(objeto.get("cedulaCliente").toString()));
                  //Se implementa la lista de detalles
                  List<Detalles> listaDetalles = new ArrayList<>();
                  Detalles detalle = new Detalles();
                  detalle.setDescripcion(objeto.get("descripcionDetalle").toString());
                  detalle.setId(Integer.parseInt(objeto.get("idDetalle").toString()));
                  detalle.setIdEquipo(objeto.get("idEquipoDetalle").toString());
                  detalle.setPrecio(Integer.parseInt(objeto.get("precio").toString()));
                  //se agrega el detalle a la lista de detalles del objeto ingreso
                  listaDetalles.add(detalle);
                  auxIngreso.setListaDetalles(listaDetalles);
                  //Se agrega el objeto Ingreso a la lista de Ingresos
                  listaIngresos.add(auxIngreso);
               } else {
                  //Si ya ha ingresos registrados en la lista
                  //Se obtiene el nombre del cliente para ver si ya está registrado o no
                  String nombreObjeto = objeto.get("nombreCliente").toString();
                  //Se itera la lista de ingresos
                  for (Ingreso ingreso : listaIngresos) {
                     String nombreIngreso = ingreso.getNombreCliente();
                     //Si existe un ingreso ya con el nombre
                     if (nombreObjeto.equals(nombreIngreso)) {
                        //Pasamos simplemente a agregar detalle a lista de detalles del ingreso
                        Detalles detalle = new Detalles();
                        detalle.setDescripcion(objeto.get("descripcionDetalle").toString());
                        detalle.setId(Integer.parseInt(objeto.get("idDetalle").toString()));
                        detalle.setIdEquipo(objeto.get("idEquipoDetalle").toString());
                        detalle.setPrecio(Integer.parseInt(objeto.get("precio").toString()));
                        //Agregamos este detalle a la lsita de detalles de ingreso
                        ingreso.getListaDetalles().add(detalle);
                     }
                  }
               }
            }
         }
      }
      return listaIngresos;
   }

   private List<LocalDate> ListaFechasSinRepetir(List<Map<String, Object>> listaCruda) {

      //listas para el retorno y para ir guardando las fechas que se van encontrando sin repetir
      List<LocalDate> aux = new ArrayList<>();
      //se itera la lista cruda apra obtener las fechas
      for (Map<String, Object> objeto : listaCruda) {
         try {
            //se obtiene la fecha
            DateTimeFormatter formatearFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime fechaHora = LocalDateTime.parse(objeto.get("fechaDetalle").toString(), formatearFecha);
            LocalDate fecha = fechaHora.toLocalDate();

            //se analiza la lista para ver si se agrega o no la fecha
            if (aux.isEmpty()) {
               aux.add(fecha);
            } else {
               boolean CoincidenciaEncontrada = false;
               for (LocalDate fechaEncontrada : aux) {
                  if (!fechaEncontrada.equals(fecha)) {
                     CoincidenciaEncontrada = false;
                  } else {
                     CoincidenciaEncontrada = true;
                     break;
                  }
               }
               //Si no se encontró coincidencia con la lista existente se agrega
               if (!CoincidenciaEncontrada) {
                  aux.add(fecha);
               }
            }
         } catch (Exception e) {
            System.out.println("Error al establecer lista de fechas unicas por: " + e);
         }
      }
      //se devuele la lista sin fechas repetidas
      return aux;
   }
}
