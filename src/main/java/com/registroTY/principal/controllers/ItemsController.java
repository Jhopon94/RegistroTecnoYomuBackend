/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.entities.Items;
import com.registroTY.principal.logica.gestionItems.RegistroItem;
import com.registroTY.principal.services.EntradaItemServicioInterfaz;
import com.registroTY.principal.services.ItemsServicioInterfaz;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal del ojeto Item////////////////////
@RestController
public class ItemsController {

   @Autowired
   private ItemsServicioInterfaz servicioItems;
   @Autowired
   private EntradaItemServicioInterfaz servicioEntradaitem;

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
         Items item  = new ObjectMapper().convertValue(contenedor.get("item"), Items.class);
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
            System.out.println("vamos a gaurdar al item " + item.getNombre() + " y a la entradaItem con precio total de: " + entradaItem.getPrecioTotal());
            return new RegistroItem(servicioItems,servicioEntradaitem, item, entradaItem).RegistrarItem();
         }
      }
   }
}
