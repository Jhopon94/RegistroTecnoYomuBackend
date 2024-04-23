/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////
import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.repository.EquipoRepo;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoServicio implements EquipoServicioInterfaz {

   @Autowired
   private EquipoRepo repoEquipo;

   @Override
   public List<Equipo> ListaEquipos() {

      System.out.println("Consultando lista de Equipos...");
      try {
         List<Equipo> listaEquipos = (List<Equipo>) repoEquipo.findAll();
         return listaEquipos;
      } catch (Exception e) {
         System.out.println("Error al obtener lista de equipos por: " + e);
         return null;
      }
   }

   @Override
   public void EliminarEquipo(String id) {

      System.out.println("Eliminando equipo...");
      try {
         repoEquipo.deleteById(id);
      } catch (Exception e) {
         System.out.println("Error al eliminar equipo por: " + e);
      }
   }

   @Override
   public Map<String, Object> GuardarEquipo(Equipo equipo) {

      System.out.println("Guardando equipo...");
      Map<String, Object> resultado = new HashMap<>();
      try {
         repoEquipo.save(equipo);
         resultado.put("mensaje", "Equipo registrado correctamente");
         resultado.put("procesoExitoso", true);
         return resultado;
      } catch (Exception e) {
         System.out.println("Error al guardar equipo por: " + e);
         resultado.put("mensaje", "Error en la aplicación al registrar el equipo");
         resultado.put("procesoExitoso", false);
         return resultado;
      }
   }

   @Override
   public List<Equipo> ListaEquiposIngresados() {
      System.out.println("Consultando equipos ingresados...");
      try {
         return repoEquipo.EquiposIngresados();
      } catch (Exception e) {
         System.out.println("Error de aplicación al buscar los equipos ingresados!");
         return null;
      }
   }

   @Override
   public List<Equipo> ListaEquiposEntregados() {
      System.out.println("Consultando equipos entregados...");
      try {
         return repoEquipo.EquiposEntregados();
      } catch (Exception e) {
         System.out.println("Error de aplicación al buscar los equipos entregados!");
         return null;
      }
   }

   @Override
   public String UltimoIDEquipo() {

      System.out.println("Verificaremos el id del último equipo registrado...");
      String id;
      try {
         Equipo equipo = new Equipo();
         if (repoEquipo.UltimoEquipo().isPresent()) {
            equipo = repoEquipo.UltimoEquipo().get();
            System.out.println("Adquirido el último equipo");
            return equipo.getId();
         } else {
            System.out.println("No hay registros...");
            return "";
         }
      } catch (Exception e) {
         System.out.println("No se pudo comprobar los registros de equipo por error de consulta...");
         return null;
      }
   }

   @Override
   public String MarcarEquipoEntregado(LocalDate fechaSalida, int diasGarantia, String id) {
      System.out.println("Vamos a marcar el " + id + " como entregado!");
      try {
         int i = repoEquipo.MarcarEquipoEntregado(fechaSalida, diasGarantia, id);
         System.out.println(i);
         return "El " + id + " marcado como entregado satisfactoriamente!";
      } catch (Exception e) {
         System.out.println("No se pudo marcar el equipo como entregado por: " + e);
         return "Error de aplicación al marcar el " + id + " como entregado";
      }
   }

   @Override
   public int ObtenerSaldoPendiente(String id) {
      System.out.println("Obteniendo saldo pendiente de " + id);
      try {
         return repoEquipo.SaldoPendiente(id);
      } catch (Exception e) {
         System.out.println("Error al obtener el saldo pendiente de " + id + " por: " + e);
         return -1;//solo apra devolver algo e informar que hubo error
      }
   }

   @Override
   public List<Map<String, Object>> ObtenerDatosDeudores() {

      System.out.println("Vamos a obtener los datos de los deudores...");
      try {
         return repoEquipo.DatosDeudores();
      } catch (Exception e) {
         System.out.println("No se pudo obtener los datos de los deudores por: + e");
         return null;
      }
   }

   @Override
   public List<Map<String, Object>> EquiposAdeudados() {
      System.out.println("Obteniendo los equipos adeudados por el cliente...");
      try {
         return repoEquipo.ListaEquiposDeudor();
      } catch (Exception e) {
         System.out.println("Error al obtener los equipos adeudados por: " + e);
         return null;
      }
   }

   @Override
   public String RegistrarAbonoEnEquipo(int abono, String id) {
      System.out.println("Actualizaremos el saldo pendiente del equipo en cuestión...");
      try {
         int saldoPendiente = ObtenerSaldoPendiente(id);
         int saldoFinal = saldoPendiente - abono;
         if (saldoFinal >= 0 && abono > 0) {
            repoEquipo.RegistrarAbono(saldoFinal, id);
            return "Abono registrado con éxito!";
         } else {
            System.out.println("El abono es más grande que la deuda por " + (abono - saldoPendiente) + " pesos!");
            return "El saldo pendiente es solo de " + saldoPendiente + " pesos!";
         }
      } catch (Exception e) {
         System.out.println("Error al registrar el abono por: " + e);
         return null;
      }
   }

   @Override
   public String CambiarEstadoEquipo(String estado, String id) {
      System.out.println("Cambiando el estado del equipo a " + estado);
      try {
         repoEquipo.CambiarEstado(estado, id);
         return "Estado cambiado a " + estado + " satisfactoriamente";
      } catch (Exception e) {
         System.out.println("Error al cambiar el estado del equipo por: " + e);
         return "Error de aplicación al cambiar el estado del equipo!";
      }
   }

   @Override
   public List<Equipo> ListaEquipoRango(LocalDate fechaInicial, LocalDate fechaFinal) {
      System.out.println("Obteniendo la lista de los equipos entregados por rango de fecha!...");
      try {
         return repoEquipo.ListaEquiposRango(fechaInicial, fechaFinal);
      } catch (Exception e) {
         System.out.println("Error al obtener lista de los equipos entregados por rango de fecha por: " + e);
         return null;
      }
   }
   
   @Override
   public List<Equipo> ListaEquipoRangoPagaron(LocalDate fechaInicial, LocalDate fechaFinal) {
      System.out.println("Obteniendo la lista de los equipos entregados por rango de fecha!...");
      try {
         return repoEquipo.ListaEquiposRangoPagaron(fechaInicial, fechaFinal);
      } catch (Exception e) {
         System.out.println("Error al obtener lista de los equipos entregados por rango de fecha por: " + e);
         return null;
      }
   }
}
