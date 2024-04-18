/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.TipoItem;
import com.registroTY.principal.logica.gestionItems.RegistroTipoItem;
import com.registroTY.principal.services.TipoItemServicioInterfaz;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal para los tipos de item////////////////////
@RestController
public class TipoItemController {
    
    @Autowired
    private TipoItemServicioInterfaz servicioTipoItem;
    
    @GetMapping("/TiposItem")
    public List<TipoItem> ListaTiposItem(){
        
        return servicioTipoItem.ListaTiposItem();
    }
    
    @PostMapping("/TiposItem")
    public String GuardarTipoItem(@Valid @RequestBody TipoItem tipoItem, BindingResult resultado){
    
        if(resultado.hasErrors()) return "Error en los datos ingresados, intenta poner nombre de usuario sin espacios.";
        return new RegistroTipoItem(servicioTipoItem, tipoItem).RegistrarTipoItem();
    }
}
