/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.repository.EntradaItemRepo;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EntradaItemServicio implements EntradaItemServicioInterfaz {
    
    @Autowired
    private EntradaItemRepo repoEntradaItem;
    
    @Override
    public List<Map<String, String>> ListaEntradaItem(){
        
        System.out.println("Consultando lista de las entradas de los items...");
        try {
        return repoEntradaItem.HistorialCompras();
        } catch (Exception e) {
            System.out.println("Error al obtener lista de entradas de items por: " + e);
            return null;
        }
    }

    @Override
    public boolean GuardarEntradaItem(EntradaItem entradaItem) {
        
        System.out.println("Guardando entrada de item...");
        try {
        repoEntradaItem.save(entradaItem);
        return true;
        } catch (Exception e) {
            System.out.println("Error al guardar entrada de ítem por: " + e);
            return false;
        }
    }
    
    
    @Override
    public String UltimaCompra(){
       System.out.println("Obteniendo el último registro de entrada de item...");
       try {
          if(repoEntradaItem.UltimaEntradaItem().isPresent()){
             EntradaItem entradaItem = repoEntradaItem.UltimaEntradaItem().get();
             System.out.println("Encontrado el registro de la última entradaItem!");
             return entradaItem.getId() + "";
          }else{
             System.out.println("No hay registros en la tabla tipoItem!");
             return "";
          }
       } catch (Exception e) {
          System.out.println("No se puedo obtener el ultimo registro de entradaItem por error en la aplicación por: " + e);
          return null;
       }
    }
    
    @Override
    public List<Map<String, Object>> ListaCompras(LocalDate fechaInicio, LocalDate fechaFin){
       System.out.println("Otteniendo la lista de las compras con el nombre del item incluido...");
       try {
          return repoEntradaItem.ListaCompras(fechaInicio,fechaFin);
       } catch (Exception e) {
          System.out.println("No se pudo obtener la lsita d elas compras por: " + e);
          return null;
       }
    }
    
    @Override
    public boolean BorrarEntradaitem(int id){
       System.out.println("Eliminaremos una compra registrada...");
       try {
          repoEntradaItem.deleteById(id);
          return true;
       } catch (Exception e) {
          System.out.println("Error al eliminar el registro de la compra...");
          return false;
       }
    }
}
