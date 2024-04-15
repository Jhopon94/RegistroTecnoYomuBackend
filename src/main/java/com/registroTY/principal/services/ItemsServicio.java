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
        
        System.out.println("consultando lista de ítems...");
        try {
        List<Items> listaItems = (List<Items>) repoItems.findAll();
        return listaItems;
        } catch (Exception e) {
            System.out.println("Error al consultar lita de ítems por: " + e);
            return null;
        }
    }

    @Override
    public void GuardarItem(Items item) {
        
        System.out.println("Guardando ítem...");
        try {
        repoItems.save(item);
        } catch (Exception e) {
            System.out.println("Error al guardar ítem por: " + e);
        }
    }
}
