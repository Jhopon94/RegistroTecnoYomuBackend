/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.Detalles;
import java.util.List;
import java.util.Map;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface DetallesServicioInterfaz {
    
    List<Detalles> ListaDetalles();
    
    void EliminarDetalle(int id);
    
    void GuardarDetalle(Detalles detalle);
    
    Map<String, Object> GuardarVariosDetalles(List<Detalles> detalles);
}
