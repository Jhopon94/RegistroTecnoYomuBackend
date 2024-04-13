/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Items;
import com.registroTY.principal.services.ItemsServicioInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void GuardarItems(@RequestBody Items items){
        System.out.println("A guardar el item " + items.getNombre());
        servicioItems.GuardarItem(items);
    }
}
