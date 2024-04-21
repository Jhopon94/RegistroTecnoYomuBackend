/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.entities.Items;
import com.registroTY.principal.logica.gestionContable.ConsultaIngresos;
import com.registroTY.principal.logica.gestionContable.ConsultarDeudores;
import com.registroTY.principal.logica.gestionContable.RegistroCompraItem;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EntradaItemServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import com.registroTY.principal.services.ItemsServicioInterfaz;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatosContablesController {

   @Autowired
   private DetallesServicioInterfaz servicioDetalles;
   @Autowired
   private EquipoServicioInterfaz servicioEquipo;
   @Autowired
   private EntradaItemServicioInterfaz servicioEntradaItem;
   @Autowired
   private ItemsServicioInterfaz servicioItems;

   //Para el caso de deudores que nor equiere fecha, poner /deudores/0/0 en el front
   @GetMapping("/DatosContables/{peticionLista}/{fechaInicioString}/{fechaFinString}")
   public List<?> ListaRequerida(@PathVariable String peticionLista, @PathVariable String fechaInicioString, @PathVariable String fechaFinString) {
      switch (peticionLista) {
         case "ingresos":
            //Si se piden ingresos, se intenta parsear las fechas
            DateTimeFormatter formateadorIngresos = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate fechaInicioIngreso = null;
            LocalDate fechaFinIngreso = null;

            try {
               System.out.println("Intentaremos parsear las fechas...");
               fechaInicioIngreso = LocalDate.parse(fechaInicioString, formateadorIngresos);
               fechaFinIngreso = LocalDate.parse(fechaFinString, formateadorIngresos);
               System.out.println("Fechas parseadas correctamente! inicio: " + fechaInicioIngreso + " fin: " + fechaFinIngreso);
               return new ConsultaIngresos(fechaInicioIngreso, fechaFinIngreso, servicioDetalles).listaIngresos();
            } catch (Exception e) {
               System.out.println("No se pudiero parsear las fechas entrantes por: " + e);
               return null;
            }
         case "compras":
            //Si se piden compras, se intenta parsear las fechas
            DateTimeFormatter formateadorCompras = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate fechaInicioCompras = null;
            LocalDate fechaFinCompras = null;

            try {
               System.out.println("Intentaremos parsear las fechas...");
               fechaInicioCompras = LocalDate.parse(fechaInicioString, formateadorCompras);
               fechaFinCompras = LocalDate.parse(fechaFinString, formateadorCompras);
               System.out.println("Fechas parseadas correctamente! inicio: " + fechaInicioCompras + " fin: " + fechaFinCompras);
               return servicioEntradaItem.ListaCompras(fechaInicioCompras, fechaFinCompras);
            } catch (Exception e) {
               System.out.println("No se pudiero parsear las fechas entrantes por: " + e);
               return null;
            }
         case "deudores":
            return new ConsultarDeudores(servicioEquipo, servicioDetalles).ListaDeudoresFull();
         default:
            throw new AssertionError();
      }
   }

   @PostMapping("/DatosContables/Compra")
   public String CompraItems(@RequestBody Map<String, Object> contenedor, BindingResult resultado) {
      Items item = new ObjectMapper().convertValue(contenedor.get("item"), Items.class);
      EntradaItem entradaItem = new ObjectMapper().convertValue(contenedor.get("entradaItem"), EntradaItem.class);

      ValidatorFactory factoria = Validation.buildDefaultValidatorFactory();
      Validator validador = factoria.getValidator();

      Set<ConstraintViolation<Items>> errorItem = validador.validate(item);
      Set<ConstraintViolation<EntradaItem>> errorEntradaItem = validador.validate(entradaItem);
      
      if(!errorItem.isEmpty()){
            return "Error en los datos enviados del objeto Item";
         }else if(!errorEntradaItem.isEmpty()){
            return "Error en los datos del objeto entradaItem!";
         }else{
            System.out.println("Vamos a registrar la compra del ítem existente...");
            return new RegistroCompraItem().RegistrarCompraNueva(entradaItem, servicioEntradaItem, item, servicioItems);
         }
   }
}
