/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.repository.EntradaItemRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EntradaItemServicio implements EntradaItemServicioInterfaz {
    
    @Autowired
    private EntradaItemRepo repoEntradaItem;
    
    @Override
    public List<EntradaItem> ListaEntradaItem(){
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<EntradaItem> listaEntradasItem = (List<EntradaItem>) repoEntradaItem.findAll();
        return listaEntradasItem;
    }
}
