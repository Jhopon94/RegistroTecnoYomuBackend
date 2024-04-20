/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.EntradaItem;
import java.util.List;
import java.util.Map;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface EntradaItemServicioInterfaz {
    
    List<Map<String, String>> ListaEntradaItem();
    
    void GuardarEntradaItem(EntradaItem entradaItem);
    
    String UltimaCompra();
    
}
