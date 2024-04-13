/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Items;
import com.registroTY.principal.repository.ItemsRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemsServicio implements ItemsServicioInterfaz {
    
    @Autowired
    private ItemsRepo repoItems;
    
    @Override
    public List<Items> ListaItems(){
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<Items> listaItems = (List<Items>) repoItems.findAll();
        return listaItems;
    }

    @Override
    public void GuardarItem(Items item) {
        
        repoItems.save(item);
    }
}
