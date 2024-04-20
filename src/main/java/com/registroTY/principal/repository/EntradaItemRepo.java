/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.EntradaItem;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaItemRepo extends CrudRepository<EntradaItem, String>{ //Integer porque el id es int
    
    @Query(value = "SELECT * FROM entradaItem ORDER BY fechaCompra DESC LIMIT 1", nativeQuery = true)
    Optional<EntradaItem> UltimaEntradaItem();
    
    @Query(value = "SELECT entradaItem.*, items.nombre, items.descripcion FROM entradaItem "
            + "INNER JOIN items ON entradaItem.idItem = items.id ", nativeQuery = true)
    List<Map<String, String>> HistorialCompras();
}
