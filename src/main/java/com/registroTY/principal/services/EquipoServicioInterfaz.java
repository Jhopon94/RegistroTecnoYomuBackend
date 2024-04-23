/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.Equipo;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface EquipoServicioInterfaz {
    
    List<Equipo> ListaEquipos();
    
    void EliminarEquipo(String id);
    
    Map<String, Object> GuardarEquipo(Equipo equipo);
    
    List<Equipo> ListaEquiposIngresados();
    
    List<Equipo> ListaEquiposEntregados();
    
    String UltimoIDEquipo();
    
    String MarcarEquipoEntregado(LocalDate fechaSalida, int diasGarantia, String id);
    
    int ObtenerSaldoPendiente(String id);
    
    List<Map<String, Object>> ObtenerDatosDeudores();
    
    List<Map<String, Object>> EquiposAdeudados();
    
    String RegistrarAbonoEnEquipo(int abono, String id);
    
    String CambiarEstadoEquipo(String estado, String id);
    
    List<Equipo> ListaEquipoRangoPagaron(LocalDate fechaInicial, LocalDate fechaFinal);
    
    List<Equipo> ListaEquipoRango(LocalDate fechaInicial, LocalDate fechaFinal);
}
