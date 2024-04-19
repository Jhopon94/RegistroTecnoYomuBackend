/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.Equipo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepo extends CrudRepository<Equipo, String>{ //Integer porque el id es int
    
    //En ambas se usa native query en lugar de jpa query a voluntad.
    
    @Query(value = "SELECT * FROM equipo where fechaSalida IS NULL", nativeQuery = true)
    List<Equipo> EquiposIngresados();
    
    @Query(value = "SELECT * FROM equipo where fechaSalida IS NOT NULL", nativeQuery = true)
    List<Equipo> EquiposEntregados();
}
