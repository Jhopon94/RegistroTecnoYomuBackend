/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.SalidaItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaItemRepo extends CrudRepository<SalidaItem, Integer>{ //Integer porque el id es int
    @Query(value = "SELECT * FROM salidaItem ORDER BY fechaUso DESC LIMIT 1", nativeQuery = true)
    Optional<SalidaItem> UltimaSalidaItem();
    
    @Query(value = "SELECT id FROM salidaItem ORDER BY fechaUso DESC LIMIT 1", nativeQuery = true)
    int UltimoIdRegistrado();
}
