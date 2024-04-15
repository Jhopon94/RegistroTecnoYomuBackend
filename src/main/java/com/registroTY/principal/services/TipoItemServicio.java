/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.TipoItem;
import com.registroTY.principal.repository.TipoItemRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TipoItemServicio implements TipoItemServicioInterfaz {
    
    @Autowired
    private TipoItemRepo repoTipoItem;
    
    @Override
    public List<TipoItem> ListaTiposItem(){
        
        System.out.println("Consultando lista de ítems...");
        try {
        List<TipoItem> listaTiposItem = (List<TipoItem>) repoTipoItem.findAll();
        return listaTiposItem;
        } catch (Exception e) {
            System.out.println("Error al consultar lista de tipos de ítem por: " + e);
            return null;
        }
    }

    @Override
    public void GuardarTipoItem(TipoItem tipoItem) {
        System.out.println("Guardando tipo de item...");
        try {
        repoTipoItem.save(tipoItem);
        } catch (Exception e) {
            System.out.println("Error al guardar tipo de ítem por: " + e);
        }
    }
}
