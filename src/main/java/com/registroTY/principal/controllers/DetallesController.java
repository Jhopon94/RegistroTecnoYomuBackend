/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetallesController {
    
    @Autowired
    private DetallesServicioInterfaz servicioDetalles;
    
    @GetMapping("/Detalles")
    public List<Detalles> ListaDetalles(){
        
        return servicioDetalles.ListaDetalles();
    }
    
}
