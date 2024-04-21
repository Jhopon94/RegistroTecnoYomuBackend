/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionContable;

import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.services.EntradaItemServicioInterfaz;


public class RegistroCompraItem {
   
   public boolean RegistrarCompra(EntradaItem entradaItem, EntradaItemServicioInterfaz servicioEntradaItem){
   
      if(servicioEntradaItem.GuardarEntradaItem(entradaItem))return true;
      else return false;
   }
}
