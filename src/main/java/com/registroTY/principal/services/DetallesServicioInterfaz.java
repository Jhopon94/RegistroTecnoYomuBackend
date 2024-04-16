/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.logica.gestionEquipos.ProcesoRegistroImpl;
import java.util.List;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface DetallesServicioInterfaz {
    
    List<Detalles> ListaDetalles();
    
    void EliminarDetalle(int id);
    
    void GuardarDetalle(Detalles detalle);
    
    ProcesoRegistroImpl GuardarVariosDetalles(List<Detalles> detalles);
}
