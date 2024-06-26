/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.TipoItem;
import java.util.List;
import java.util.Optional;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface TipoItemServicioInterfaz {
    
    List<TipoItem> ListaTiposItem();
    
    String GuardarTipoItem(TipoItem tipoItem);
    
    String ConsultarExistenciaTipoItem(TipoItem tipoItem);
    
    String ConsultarUltimoTipoItem();
}
