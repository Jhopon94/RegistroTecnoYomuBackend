/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.services.EntradaItemServicioInterfaz;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal de la al ingresar items////////////////////
@RestController
public class EntradaItemController {
    
    @Autowired
    private EntradaItemServicioInterfaz servicioEntradaItem;
    
    @GetMapping("/EntradaItem")
    public List<Map<String, String>> ListaEntradasItem(){
        
        return servicioEntradaItem.ListaEntradaItem();
    }
    
    @PostMapping("/EntradaItem")
    public void GuardarEntradaItem(@RequestBody EntradaItem entradaItem){
        
        servicioEntradaItem.GuardarEntradaItem(entradaItem);
    }
}
