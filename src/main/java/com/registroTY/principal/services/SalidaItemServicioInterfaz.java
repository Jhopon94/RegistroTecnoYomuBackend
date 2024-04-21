/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.SalidaItem;
import java.util.List;
import java.util.Optional;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface SalidaItemServicioInterfaz {
    
    List<SalidaItem> ListaSalidasItem();
    
    boolean GuardarSalidaItem(SalidaItem salidaItem);
    
    int UltimaSalidaItem();
    
    boolean EliminarRegsitroSalidaItem(int id);
    
    int ObtenerUltimoID();
}
