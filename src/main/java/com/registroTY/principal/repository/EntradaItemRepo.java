/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.EntradaItem;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaItemRepo extends CrudRepository<EntradaItem, Integer>{ //Integer porque el id es int
    
    @Query(value = "SELECT * FROM entradaItem ORDER BY fechaCompra DESC LIMIT 1", nativeQuery = true)
    Optional<EntradaItem> UltimaEntradaItem();
    
    @Query(value = "SELECT entradaItem.*, items.nombre, items.descripcion FROM entradaItem "
            + "INNER JOIN items ON entradaItem.idItem = items.id ", nativeQuery = true)
    List<Map<String, String>> HistorialCompras();
    
    @Query(value = "SELECT ei.*, i.nombre FROM entradaItem ei INNER JOIN items i ON i.id = ei.idItem "
            + "WHERE ei.fechaCompra BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Map<String, Object>> ListaCompras(LocalDate fechaInicio, LocalDate fechaFin);
}
