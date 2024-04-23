/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionEquipos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jhopon
 */
public class EditarEquipos {

   EquipoServicioInterfaz servicioEquipo;
   DetallesServicioInterfaz servicioDetalles;

   public EditarEquipos(EquipoServicioInterfaz servicioEquipo, DetallesServicioInterfaz servicioDetalles) {

      this.servicioDetalles = servicioDetalles;
      this.servicioEquipo = servicioEquipo;
   }

   public String MarcarEntregado(Map<String, Object> objetos) {
      LocalDate fecha = LocalDate.now();
      String diasGarantiaTexto = objetos.get("diasGarantia").toString();
      String id = objetos.get("id").toString();
      int diasGarantia = Integer.parseInt(diasGarantiaTexto);

      int saldoEquipo = servicioEquipo.ObtenerSaldoPendiente(id);

      if (saldoEquipo >= 0) {

         if (saldoEquipo == 0) {
            return servicioEquipo.MarcarEquipoEntregado(fecha, diasGarantia, id);
         } else {
            return "No se puede marcar como entregado porque el cliente aún debe " + saldoEquipo + " pesos!";
         }

      } else {
         return "Error al consultar el saldo de " + id;
      }
   }

   public String EditarEquipoDetalles(Map<String, Map<String, Object>> opciones, ObjectMapper mapeadorObjetos) {
      //sacamos el contenedor en forma de map para ser enviado a la función post
      Map<String, Object> contenedorCrudo = opciones.get("contenedor");
      //Sacamos el contenedor de las listas de los IDS
      Map<String, Object> listasIDS = opciones.get("listasDetalles");
      try {
         //Lo casteamos al tipo de objeto automaticamente con SPRING con try catch
         ContEquipoDetallesImpl contenedor = mapeadorObjetos.convertValue(contenedorCrudo, ContEquipoDetallesImpl.class);
         Equipo equipo = contenedor.getEquipo();
         List<Detalles> detalles = contenedor.getDetalles();
         //Sección para acomodar los detalles eliminados, agregados y no cambiados.
         //1. Se obtiene las lista de los ids de los detalles
         try {
            //Se intenta convertir las listas de detalles a List<Detalles>
            //new TypeReference<List<Detalles>>() {} es como un contenedor temporal de estructura de objetos
            List<Detalles> detallesEliminados = mapeadorObjetos.convertValue(listasIDS.get("eliminados"), new TypeReference<List<Detalles>>() {
            });
            List<Detalles> detallesNuevos = mapeadorObjetos.convertValue(listasIDS.get("nuevos"), new TypeReference<List<Detalles>>() {
            });
            List<Detalles> detallesEditados = mapeadorObjetos.convertValue(listasIDS.get("editados"), new TypeReference<List<Detalles>>() {
            });

            //Procedemos a eliminar
            try {
               List<Integer> eliminadosInt = new ArrayList<>();
               //Recogemos los ids de los detalles para eliminarlos todos
               for (Detalles detalle : detallesEliminados) {
                  eliminadosInt.add(detalle.getId());
               }
               //Ahora si procedemos a eliminarlos todos
               servicioDetalles.EliminarVariosDetalles(eliminadosInt);

               //Procedemos a registrar
               for (Detalles detalle : detallesNuevos) {
                  detalle.setIdEquipo(equipo.getId());
               }
               try {
                  servicioDetalles.GuardarVariosDetalles(detallesNuevos);
                  //Procedemos a editar los editados
                  try {
                     servicioDetalles.GuardarVariosDetalles(detallesEditados);
                     try {
                        //Una vez Acomodados los detalles, procedemos a registrar la edición del equipo
                        Map<String, Object> guardarEquipo = servicioEquipo.GuardarEquipo(equipo);
                        if ((boolean) guardarEquipo.get("procesoExitoso")) {
                           return "Detalles y equipo corregidos correctamente!";
                        } else {
                           return "Editados los detalles, pero no así el equipo!";
                        }
                     } catch (Exception e) {
                        return "Se editaron todos los detalles correctamente, pero no el equipo";
                     }

                  } catch (Exception e) {
                     return "Se eliminaron los eliminados, se registraron los nuevos, pero no se editaron los editados";
                  }

               } catch (Exception e) {
                  return "Se eliminaron los detalles eliminados pero no se registraron los nuevos!";
               }
            } catch (Exception e) {
               System.out.println("No se eliminaron los detalles eliminados por : " + e);
               return "No se eliminaron los detalles eliminados";
            }

         } catch (Exception e) {
            System.out.println("No se pudieron convertir las listas de los detalles por: " + e);
            return "No se pudieron convertir las listas de los detalles.";
         }
      } catch (Exception e) {
         System.out.println("Error general al editar equipo ingresado por: " + e);
         return "Error en controlador al editar el equipo ingresado";
      }
   }

   public String AbonoEquipo(int abono, String idEquipo) {

      return servicioEquipo.RegistrarAbonoEnEquipo(abono, idEquipo);
   }

   public String CambiarEstado(String estado, String idEquipo) {
      return servicioEquipo.CambiarEstadoEquipo(estado, idEquipo);
   }

}
