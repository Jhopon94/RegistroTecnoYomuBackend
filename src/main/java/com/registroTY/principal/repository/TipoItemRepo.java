/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.TipoItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoItemRepo extends CrudRepository<TipoItem, String>{ //Integer porque el id es int
    
    boolean existsByTipoDeItem(String tipoDeItem);
    
    @Query(value = "SELECT * FROM tipoItem ORDER BY fechaCreacion DESC LIMIT 1", nativeQuery = true)
    Optional<TipoItem> UltimoTipoItem();
}
