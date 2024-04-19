/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.Detalles;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesRepo extends CrudRepository<Detalles, Integer>{ //Integer porque el id es int
    
    @Query(value = "SELECT * FROM detalles WHERE fechaRegistro BETWEEN ?1 AND ?2", nativeQuery = true)
    List<?> findAllBetweenFechaRegistro(Date startDate, Date endDate);
    
    @Query(value = "SELECT c.nombre AS nombreCliente, c.id AS cedulaCliente, d.precio AS precio," +
            "d.id AS idDetalle, d.fechaRegistro AS fechaDetalle, d.descripcion AS descripcionDetalle " +
            "FROM detalles d " + 
            "INNER JOIN equipo e ON d.idEquipo = e.id " +
            "INNER JOIN cliente c ON e.idCliente = c.id " + 
            "WHERE d.fechaRegistro BETWEEN ?1 AND ?2", nativeQuery = true)
    List<?> listaIngresosRaw(Date fechaInicio, Date fechaFin);
}
