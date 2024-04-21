/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.logica.gestionEquipos.ConsultaEquipos;
import com.registroTY.principal.logica.gestionEquipos.ContEquipoDetallesImpl;
import com.registroTY.principal.logica.gestionEquipos.RegistroEquipo;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal del ojeto Equipo////////////////////
@RestController
public class EquipoController {

   @Autowired
   private EquipoServicioInterfaz servicioEquipo;
   @Autowired
   private DetallesServicioInterfaz servicioDetalles;
   @Autowired
   //El grandioso mapeador de objetos de spring!!!
   private ObjectMapper mapeadorObjetos;

   @GetMapping("/Equipos/{estadoEquipo}")
   public List<Equipo> ListaEquipos(@PathVariable String estadoEquipo) {

      ConsultaEquipos consultaEquipos = new ConsultaEquipos(estadoEquipo, servicioEquipo);

      //primero verificar null antes que empty para evitar exepciones
      if (consultaEquipos.ListaEquipos() != null) {
         List<Equipo> listaResultado = consultaEquipos.ListaEquipos();
         if (listaResultado.isEmpty()) {
            System.out.println("Lista encontrada pero vacía!");
         }
         return listaResultado;
      } else {
         System.out.println("Error en la variable enviada!");
         return null;
      }
   }

   @PostMapping("/Equipos")
   public Map<String, Object> GuardarEquipo(@Valid @RequestBody ContEquipoDetallesImpl contenedorObjetos, BindingResult resultado) {

      if (resultado.hasErrors()) {
         Map<String, Object> aux = new HashMap<>();
         aux.put("mensaje", "Hay algún dato incorrecto");
         aux.put("procesoExitoso", false);
         return aux;
      } else {
         Equipo equipo = contenedorObjetos.getEquipo();
         List<Detalles> detalles = contenedorObjetos.getDetalles();

         RegistroEquipo registrarEquipo = new RegistroEquipo(equipo, detalles, servicioEquipo, servicioDetalles);
         return registrarEquipo.RegistrarEquipo();
      }

   }

   @DeleteMapping("/Equipos/{id}")
   public void EliminarEquipos(@PathVariable String id) {

      servicioEquipo.EliminarEquipo(id);
   }

   @PutMapping("/Equipos")
   public String EquipoEntregado(@RequestBody Map<String, Map<String, Object>> opciones) {

      //para determinar que key contiene el Map
      boolean marcarEntregado = opciones.containsKey("marcarEntregado");
      boolean edicion = opciones.containsKey("actualizar");
      boolean abono = opciones.containsKey("abono");
      boolean cambiarEstado = opciones.containsKey("cambiarEstado");

      //Si se envió un Marcar como Entregado!
      if (marcarEntregado) {

         //saco los datos apra marcar entregado
         Map<String, Object> objetos = opciones.get("marcarEntregado");

         String fecha = objetos.get("fecha").toString();
         String diasGarantiaTexto = objetos.get("diasGarantia").toString();
         String id = objetos.get("id").toString();
         int diasGarantia = Integer.parseInt(diasGarantiaTexto);

         int saldoEquipo = servicioEquipo.ObtenerSaldoPendiente(id);

         if (saldoEquipo != 333) {

            if (saldoEquipo == 0) {
               try {
                  DateTimeFormatter formateador = DateTimeFormatter.ofPattern("ddMMyyyy");
                  LocalDate fechaFormateada = LocalDate.parse(fecha, formateador);
                  return servicioEquipo.MarcarEquipoEntregado(fechaFormateada, diasGarantia, id);
               } catch (Exception e) {
                  return "Error en en la conversión de la fecha o envío de parámetros!";
               }
            } else {
               return "No se puede marcar como entregado porque el cliente aún debe " + saldoEquipo + " pesos!";
            }

         } else {
            return "Error al consultar el saldo de " + id;
         }

      } else if (edicion) { //Se envió para edición

         //sacamos el contenedor en forma de map para ser enviado a la función post
         Map<String, Object> contenedorCrudo = opciones.get("contenedor");
         //Lo casteamos al tipo de objeto automaticamente con SPRING
         ContEquipoDetallesImpl contenedor = mapeadorObjetos.convertValue(contenedorCrudo, ContEquipoDetallesImpl.class);
         //Creamos el BindingResult falso para enviar
         BindingResult resultadoFalso = new BeanPropertyBindingResult(contenedor, "contenedor");

         //Para obtner el resultado del registro del equipo al registrarlo de una vez:
         Map<String, Object> resultadoRegistroEquipo = GuardarEquipo(contenedor, resultadoFalso);
         //Si el registro del equipo fue exitoso
         if ((boolean) resultadoRegistroEquipo.get("procesoExitoso")) {
            //Obtenemos lista de ids y las casteamos a list int
            List<Integer> listaIDS = (List<Integer>) contenedorCrudo.get("detalles");
            return servicioDetalles.EliminarVariosDetalles(listaIDS);
         } else {
            System.out.println("No se pudo actualizar el equipo, por ende no se eliminaron detalles");
         }
      } else if (abono) {
         int abonoValor = Integer.parseInt(opciones.get("abono").get("valor").toString());
         String idEquipoAbono = opciones.get("abono").get("id").toString();
         return servicioEquipo.RegistrarAbonoEnEquipo(abonoValor, idEquipoAbono);
      }else if(cambiarEstado){
         String estado = opciones.get("cambiarEstado").get("estado").toString();
         String idEquipoEstado = opciones.get("cambiarEstado").get("id").toString();
         return servicioEquipo.CambiarEstadoEquipo(estado, idEquipoEstado);
      }
      return "prueba";
   }

}
