/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.entities.Items;
import com.registroTY.principal.entities.SalidaItem;
import com.registroTY.principal.logica.gestionContable.RegistroSalidaItem;
import com.registroTY.principal.logica.gestionItems.RegistroItem;
import com.registroTY.principal.services.EntradaItemServicioInterfaz;
import com.registroTY.principal.services.ItemsServicioInterfaz;
import com.registroTY.principal.services.SalidaItemServicioInterfaz;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal del ojeto Item////////////////////
@RestController
public class ItemsController {

   @Autowired
   private ItemsServicioInterfaz servicioItems;
   @Autowired
   private EntradaItemServicioInterfaz servicioEntradaitem;
   @Autowired
   private SalidaItemServicioInterfaz servicioSalidaItem;

   @GetMapping("/Items")
   public List<Items> ListaItems() {

      return servicioItems.ListaItems();
   }

   @PostMapping("/Items")
   public String GuardarItems(@RequestBody Map<String, Object> contenedor, BindingResult resultado) {

      if (resultado.hasErrors()) {
         return "Error en la información enviada!!";
      } else {

         //Validamos como lo haría @Valid
         Items item = new ObjectMapper().convertValue(contenedor.get("item"), Items.class);
         EntradaItem entradaItem = new ObjectMapper().convertValue(contenedor.get("entradaItem"), EntradaItem.class);

         ValidatorFactory factoria = Validation.buildDefaultValidatorFactory();
         Validator validador = factoria.getValidator();

         Set<ConstraintViolation<Items>> errorItem = validador.validate(item);
         Set<ConstraintViolation<EntradaItem>> errorEntradaItem = validador.validate(entradaItem);

         if (!errorItem.isEmpty()) {
            return "Error en los datos enviados del objeto Item";
         } else if (!errorEntradaItem.isEmpty()) {
            return "Error en los datos del objeto entradaItem!";
         } else {
            System.out.println("vamos a guardar al item " + item.getNombre() + " y a la entradaItem con precio total de: " + entradaItem.getPrecioTotal());
            return new RegistroItem(servicioItems, servicioEntradaitem, item, entradaItem).RegistrarItem();
         }
      }
   }

   @PutMapping("/ItemOff")
   public String SalidaItemReparador(@Valid @RequestBody SalidaItem salidaItem, BindingResult resultado) {
      if (resultado.hasErrors()) {
         return "Error en los datos de la salida del ítem! por : " + resultado;
      } else {
         return new RegistroSalidaItem(servicioItems, servicioSalidaItem, salidaItem).RegistrarUsoItem();
      }
   }

   @PutMapping("/ItemPlus")
   public String NuevaCompraItem(@RequestBody Map<String, Object> detalles, BindingResult resultado) {
      if (resultado.hasErrors()) {
         return "Error en los datos ingresados!";
      } else {
         String idItem = detalles.get("id").toString();
         int cantidadCompra = Integer.parseInt(detalles.get("cantidad").toString());
         int saldoActual = servicioItems.ObtenerSaldoItem(idItem);
         int precioTotal = Integer.parseInt(detalles.get("precioTotal").toString());
         int costoUnitario = Integer.parseInt(detalles.get("costoUnitario").toString());

         if (saldoActual >= 0) {
            try {
               servicioItems.CambiarSaldoItem(saldoActual + cantidadCompra, idItem);
               System.out.println("Saldo modificado con éxito");
               EntradaItem entradaItem = new EntradaItem();
               entradaItem.setIdItem(idItem);
               entradaItem.setCantidad(cantidadCompra);
               entradaItem.setCostoUnitario(costoUnitario);
               entradaItem.setPrecioTotal(precioTotal);
               if (servicioEntradaitem.GuardarEntradaItem(entradaItem)) {
                  return "Éxito al cambiar el saldo del ítem y al registrar la compra!";
               } else {
                  return "El sald del ítem se cambió, pero no se registró la compra!";
               }
            } catch (Exception e) {
               System.out.println("Error al registrar la compra y al editar el saldo del ítem por: " + e);
               return "Error al registrar la compra y al editar el saldo del ítem";
            }

         } else {
            return "Error al modificar el saldo";
         }
      }
   }

   @PutMapping("/ItemEdit")
   public String EditarItem(@Valid @RequestBody Items item, BindingResult resultado){
      if(resultado.hasErrors()){
         return "Error en los datos enviados";
      }else{
         servicioItems.GuardarItem(item);
         return "Ítem editado con éxito!";
      }
   }

}
