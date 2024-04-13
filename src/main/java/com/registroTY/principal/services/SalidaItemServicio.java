/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.SalidaItem;
import com.registroTY.principal.repository.SalidaItemRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SalidaItemServicio implements SalidaItemServicioInterfaz {
    
    @Autowired
    private SalidaItemRepo repoSalidaItem;
    
    @Override
    public List<SalidaItem> ListaSalidasItem(){
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<SalidaItem> listaSalidasItem = (List<SalidaItem>) repoSalidaItem.findAll();
        return listaSalidasItem;
    }
}
