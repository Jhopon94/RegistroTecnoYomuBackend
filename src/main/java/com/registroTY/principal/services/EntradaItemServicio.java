/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.repository.EntradaItemRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EntradaItemServicio implements EntradaItemServicioInterfaz {
    
    @Autowired
    private EntradaItemRepo repoEntradaItem;
    
    @Override
    public List<EntradaItem> ListaEntradaItem(){
        
        System.out.println("Consultando lista de las entradas de los items...");
        try {
        List<EntradaItem> listaEntradasItem = (List<EntradaItem>) repoEntradaItem.findAll();
        return listaEntradasItem;
        } catch (Exception e) {
            System.out.println("Error al obtener lista de entradas de items por: " + e);
            return null;
        }
    }

    @Override
    public void GuardarEntradaItem(EntradaItem entradaItem) {
        
        System.out.println("Guardando entrada de item...");
        try {
        repoEntradaItem.save(entradaItem);
        } catch (Exception e) {
            System.out.println("Error al guardar entrada de ítem por: " + e);
        }
    }
    
    @Override
    public String UltimaCompra(){
       System.out.println("Obteniendo el último registro de entrada de item...");
       try {
          if(repoEntradaItem.UltimaEntradaItem().isPresent()){
             EntradaItem entradaItem = repoEntradaItem.UltimaEntradaItem().get();
             System.out.println("Encontrado el registro de la última entradaItem!");
             return entradaItem.getId();
          }else{
             System.out.println("No hay registros en la tabla tipoItem!");
             return "";
          }
       } catch (Exception e) {
          System.out.println("No se puedo obtener el ultimo registro de entradaItem por error en la aplicación por: " + e);
          return null;
       }
    }
}
