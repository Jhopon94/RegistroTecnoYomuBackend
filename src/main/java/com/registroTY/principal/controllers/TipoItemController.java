/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.TipoItem;
import com.registroTY.principal.services.TipoItemServicioInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoItemController {
    
    @Autowired
    private TipoItemServicioInterfaz servicioTipoItem;
    
    @GetMapping("/TiposItem")
    public List<TipoItem> ListaTiposItem(){
        
        return servicioTipoItem.ListaTiposItem();
    }
    
}
