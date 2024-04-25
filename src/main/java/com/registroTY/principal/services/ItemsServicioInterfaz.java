/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.Items;
import java.util.List;
import java.util.Optional;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface ItemsServicioInterfaz {
    
    List<Items> ListaItems();
    
    boolean GuardarItem(Items item);
    
    String ConsultarExistenciaItem(Items item);
    
    String findUltimoItem();
    
    boolean BorrarItem(String id);
    
    int ObtenerSaldoItem(String id);
    
    boolean CambiarSaldoItem(int saldoNuevo, String id);
    
    boolean EliminarRegistroCompra(String id);
    
    int ConsuoltarExistenciaPorID(String id);
}
