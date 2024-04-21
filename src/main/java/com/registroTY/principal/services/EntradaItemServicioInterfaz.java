/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.EntradaItem;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface EntradaItemServicioInterfaz {
    
    List<Map<String, String>> ListaEntradaItem();
    
    boolean GuardarEntradaItem(EntradaItem entradaItem);
    
    String UltimaCompra();
    
    List<Map<String, Object>> ListaCompras(LocalDate fechaInicio, LocalDate fechaFin);
    
}
