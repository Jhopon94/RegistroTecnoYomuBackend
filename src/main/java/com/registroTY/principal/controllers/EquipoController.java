/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.logica.gestionEquipos.ConsultaEquipos;
import com.registroTY.principal.logica.gestionEquipos.ContEquipoDetallesImpl;
import com.registroTY.principal.logica.gestionEquipos.EditarEquipos;
import com.registroTY.principal.logica.gestionEquipos.RegistroEquipo;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
   public String EquipoEntregado(@RequestBody Map<String, Map<String, Object>> opciones, BindingResult resultado) {

      if(!resultado.hasErrors()){
            
      //para determinar que key contiene el Map
      boolean marcarEntregado = opciones.containsKey("marcarEntregado");
      boolean edicion = opciones.containsKey("actualizar");
      boolean abono = opciones.containsKey("abono");
      boolean cambiarEstado = opciones.containsKey("cambiarEstado");

      //Si se envió un Marcar como Entregado!
      if (marcarEntregado) {
         //saco los datos para marcar entregado
         return new EditarEquipos(servicioEquipo, servicioDetalles).MarcarEntregado(opciones.get("marcarEntregado"));

      } else if (edicion) {

         return new EditarEquipos(servicioEquipo, servicioDetalles).EditarEquipoDetalles(opciones, mapeadorObjetos);

      } else if (abono) {
         int abonoValor = Integer.parseInt(opciones.get("abono").get("valor").toString());
         String idEquipoAbono = opciones.get("abono").get("id").toString();
         return new EditarEquipos(servicioEquipo, servicioDetalles).AbonoEquipo(abonoValor, idEquipoAbono);

      } else if (cambiarEstado) {
         String estado = opciones.get("cambiarEstado").get("estado").toString();
         String idEquipoEstado = opciones.get("cambiarEstado").get("id").toString();
         return new EditarEquipos(servicioEquipo, servicioDetalles).CambiarEstado(estado, idEquipoEstado);
      }
      return "prueba";
      }else{
         return "El objeto enviado contiene error de estructura";
      }

   }

}
