/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionItems;

import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.entities.Items;
import com.registroTY.principal.logica.TresUltimos;
import com.registroTY.principal.logica.gestionContable.RegistroCompraItem;
import com.registroTY.principal.services.EntradaItemServicioInterfaz;
import com.registroTY.principal.services.ItemsServicioInterfaz;



public class RegistroItem {
    
    ItemsServicioInterfaz servicioItem;
    Items item;
    String mensaje;
    RegistroCompraItem registroCompra;
    EntradaItem entradaItem;
    EntradaItemServicioInterfaz servicioEntradaItem;
    
    public RegistroItem(ItemsServicioInterfaz servicioItem,EntradaItemServicioInterfaz servicioEntradaItem, Items item, EntradaItem entradaItem){
    
        this.servicioItem = servicioItem;
        this.servicioEntradaItem = servicioEntradaItem;
        this.item = item;
        this.entradaItem = entradaItem;
        registroCompra = new RegistroCompraItem();
    }
    
    public String RegistrarItem(){
    
        //Si item no existe
        //Como es el registro, la cantidad entrante del item se define como el saldo
        if(!ItemExiste()){
            EstablecerIDItem(servicioItem.findUltimoItem());
            if(servicioItem.GuardarItem(item)){
               if(registroCompra.RegistrarCompra(entradaItem, servicioEntradaItem)){
                  return "Item y compra registrados correctamente!";
               }else{
                  if(servicioItem.BorrarItem(item.getId())) return "Se registró el ítem pero no la compra, por lo que se borró el ítem previamente registrado de forma automática!";
                  else return "QUedó registrado solamente el ítem, ya que no se pudo borrar al fallar el registro de la compra!";
               }
            }else{
               return "No se pudo registrar el ítem ni los detalles por error en la aplicación";
            }
        }
        else return mensaje;
    }
    
    private boolean ItemExiste(){
        
        mensaje = servicioItem.ConsultarExistenciaItem(item);
        //Si se devuelve el nombre del item
        return !mensaje.equals(item.getNombre());
    }
    
    private void EstablecerIDItem(String id){
       System.out.println("vamos a establecer el id del item y la foránea de tipo ítem");
       try {
          if(id != null){
          if(id.equals("")){
             String idNuevo = "item000";
             item.setId(idNuevo);
             //Lo agregamos d una vez como llave foránea de la entrada
             entradaItem.setIdItem(id);
          }else{
             String idNuevo = new TresUltimos().IdNuevo(id);
             item.setId(idNuevo);
             //Lo agregamos d una vez como llave foránea de la entrada
             entradaItem.setIdItem(idNuevo);
          }
       }else{
          System.out.println("El id del ultimo item es null");
       }
       } catch (Exception e) {
          System.out.println("Error al establecer id de item y EntradaItem por: " + e);
       }
       
    }
}
