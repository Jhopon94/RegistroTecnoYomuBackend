/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionItems;

import com.registroTY.principal.entities.Items;
import com.registroTY.principal.logica.TresUltimos;
import com.registroTY.principal.services.ItemsServicioInterfaz;



public class RegistroItem {
    
    ItemsServicioInterfaz servicioItem;
    Items item;
    String mensaje;
    
    public RegistroItem(ItemsServicioInterfaz servicioItem, Items item){
    
        this.servicioItem = servicioItem;
        this.item = item;
    }
    
    public String RegistrarItem(){
    
        //Si item no existe
        //Como es el registro, la cantidad entrante del item se define como el saldo
        if(!ItemExiste()){
            EstablecerIDItem(servicioItem.findUltimoItem());
            return servicioItem.GuardarItem(item);
        }
        else return mensaje;
    }
    
    private boolean ItemExiste(){
        
        mensaje = servicioItem.ConsultarExistenciaItem(item);
        //Si se devuelve el nombre del item
        return !mensaje.equals(item.getNombre());
    }
    
    private void EstablecerIDItem(String id){
       if(id != null){
          if(id.equals("")){
             item.setId("item000");
          }else{
             item.setId(new TresUltimos().IdNuevo(id));
          }
       }
    }
}
