/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Items;
import com.registroTY.principal.entities.SalidaItem;
import com.registroTY.principal.services.SalidaItemServicioInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal al usar items en reparación////////////////////
@RestController
public class SalidaItemController {
    
    @Autowired
    private SalidaItemServicioInterfaz servicioSalidaItem;
    
    @GetMapping("/SalidasItem")
    public List<SalidaItem> ListaSalidasItem(){
        
        return servicioSalidaItem.ListaSalidasItem();
    }
    
    @PostMapping("/SalidasItem")
    public void GuardarSalidaItem(@RequestBody SalidaItem salidaItem){
    
        servicioSalidaItem.GuardarSalidaItem(salidaItem);
    }
    
}
