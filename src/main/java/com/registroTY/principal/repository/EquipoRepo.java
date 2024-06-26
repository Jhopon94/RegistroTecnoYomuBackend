/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.repository;

//////////////Esta interface nos evita escribir las sentencias sql ////////////////////

import com.registroTY.principal.entities.Equipo;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
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
    
    @Query(value = "SELECT * FROM equipo ORDER BY fechaIngreso DESC LIMIT 1", nativeQuery = true)
    Optional<Equipo> UltimoEquipo();
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE equipo SET fechaSalida = ?1, diasGarantia = ?2 WHERE id = ?3", nativeQuery = true)
    int MarcarEquipoEntregado(LocalDate fechaSalida, int diasGarantia, String id);
    
    @Query(value = "SELECT saldoPendiente FROM equipo WHERE id = ?1", nativeQuery = true)
    int SaldoPendiente(String id);
    
    @Query(value = "SELECT c.id, c.nombre FROM equipo e INNER JOIN "
            + "cliente c ON c.id = e.idCliente WHERE e.saldoPendiente > 0", nativeQuery = true)
    List<Map<String, Object>> DatosDeudores();
    
    @Query(value = "SELECT e.id, e.modelo, e.saldoPendiente, e.fechaIngreso FROM equipo e INNER JOIN cliente c ON c.id = e.idCliente WHERE e.saldoPendiente > 0", nativeQuery = true)
    List<Map<String, Object>> ListaEquiposDeudor();
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE equipo SET saldoPendiente = ?1 WHERE id=?2", nativeQuery = true)
    int RegistrarAbono(int saldoCalculado, String id);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE equipo SET estadoEquipo = ?1 WHERE id=?2", nativeQuery = true)
    void CambiarEstado(String estado, String id);
    
    @Query(value = "SELECT * FROM equipo WHERE fechaSalida BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Equipo> ListaEquiposRango(LocalDate fechaInicial, LocalDate fechaFinal);
    
    @Query(value = "SELECT * FROM equipo WHERE fechaSalida BETWEEN ?1 AND ?2 AND fechaSalida IS NOT NULL", nativeQuery = true)
    List<Equipo> ListaEquiposRangoPagaron(LocalDate fechaInicial, LocalDate fechaFinal);
    
}
