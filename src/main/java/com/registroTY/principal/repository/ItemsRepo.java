/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.Items;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepo extends CrudRepository<Items, String>{ //Integer porque el id es int
    
    /* Al parecer, al acomodar la función de esta manera, spring la interpreta solito
    y me genera una búsqueda por "nombre", donde este nombre es la columna donde deseo buscar que 
    me imagino debería ser igual a la variable*/
    boolean existsByNombre(String nombre);
    
    @Query(value = "SELECT * FROM items ORDER BY fechaCreacion DESC LIMIT 1", nativeQuery = true)
    Optional<Items> UltimoItem();
    
    @Query(value = "SELECT saldo FROM items WHERE id = ?1", nativeQuery = true)
    int ObtenerSaldoItem(String id);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE items SET saldo = ?1 WHERE id = ?2", nativeQuery = true)
    int NuevoSaldo(int saldo, String idItem);
}
