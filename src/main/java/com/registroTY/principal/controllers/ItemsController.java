/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Items;
import com.registroTY.principal.logica.gestionItems.RegistroItem;
import com.registroTY.principal.services.ItemsServicioInterfaz;
import jakarta.validation.Valid;
import java.util.List;
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
    
    @GetMapping("/Items")
    public List<Items> ListaItems(){
        
        return servicioItems.ListaItems();
    }
    
    @PostMapping("/Items")
    public String GuardarItems(@Valid @RequestBody Items item, BindingResult resultado){
        
        if(resultado.hasErrors())return "Error en el llenado de los datos del ítem!";
        return new RegistroItem(servicioItems,item).RegistrarItem();
    }
}
