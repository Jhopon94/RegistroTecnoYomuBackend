/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionItems;

import com.registroTY.principal.entities.TipoItem;
import com.registroTY.principal.logica.TresUltimos;
import com.registroTY.principal.services.TipoItemServicioInterfaz;



public class RegistroTipoItem {
    
    TipoItemServicioInterfaz servicioTipoItem;
    TipoItem tipoItem;
    String mensaje;
    
    public RegistroTipoItem(TipoItemServicioInterfaz servicioTipoItem, TipoItem tipoItem){
    
        this.servicioTipoItem = servicioTipoItem;
        this.tipoItem = tipoItem;
    }
    
    public String RegistrarTipoItem(){
    
        //Si tipoItem no existe
        if(!TipoItemExiste()){
           EstablecerIDTipoItem(servicioTipoItem.ConsultarUltimoTipoItem());
           return servicioTipoItem.GuardarTipoItem(tipoItem);
        } 
        else return mensaje;
    }
    
    private boolean TipoItemExiste(){
        
        mensaje = servicioTipoItem.ConsultarExistenciaTipoItem(tipoItem);
        //Si se devuelve el nombre del tipoItem
        return !mensaje.equals(tipoItem.getTipoDeItem());
    }
    
    public void EstablecerIDTipoItem(String id){
       if(id != null){
          if(id.equals("")){
             tipoItem.setId("tipoItem000");
          }else{
             tipoItem.setId(new TresUltimos().IdNuevo(id));
          }
       }
    }
}
