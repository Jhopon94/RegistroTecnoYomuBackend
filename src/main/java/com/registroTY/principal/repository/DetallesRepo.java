/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.Detalles;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesRepo extends CrudRepository<Detalles, Integer>{ //Integer porque el id es int
    
    @Query(value = "SELECT * FROM detalles WHERE fechaRegistro BETWEEN ?1 AND ?2", nativeQuery = true)
    List<?> findAllBetweenFechaRegistro(Date startDate, Date endDate);
}
