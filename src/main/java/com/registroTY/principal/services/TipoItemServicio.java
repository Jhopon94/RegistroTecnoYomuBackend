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
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<TipoItem> listaTiposItem = (List<TipoItem>) repoTipoItem.findAll();
        return listaTiposItem;
    }
}
