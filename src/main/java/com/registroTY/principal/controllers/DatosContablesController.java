/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.logica.gestionContable.ConsultaIngresos;
import com.registroTY.principal.logica.gestionContable.ConsultarDeudores;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatosContablesController {

    @Autowired
    private DetallesServicioInterfaz servicioDetalles;
    @Autowired
    private EquipoServicioInterfaz servicioEquipo;

    @GetMapping("/DatosContables/{peticionLista}/{fechaInicioString}/{fechaFinString}")
    public List<?> ListaRequerida(@PathVariable String peticionLista, @PathVariable String fechaInicioString, @PathVariable String fechaFinString) {
        switch (peticionLista) {
            case "ingresos":
                //Si se piden ingresos, se intenta parsear las fechas
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("ddMMyyyy");
                LocalDate fechaInicio = null;
                LocalDate fechaFin = null;
                
                try {
                    System.out.println("Intentaremos parsear las fechas...");
                    fechaInicio = LocalDate.parse(fechaInicioString, formateador);
                    fechaFin = LocalDate.parse(fechaFinString, formateador);
                    System.out.println("Fechas parseadas correctamente! inicio: " + fechaInicio + " fin: " + fechaFin);
                    return new ConsultaIngresos(fechaInicio, fechaFin, servicioDetalles).listaIngresos();
                } catch (Exception e) {
                    System.out.println("No se pudiero parsear las fechas entrantes por: " + e);
                    return null;
                }
            case "compras":
               
                return null;
            case "deudores":
                  return new ConsultarDeudores(servicioEquipo, servicioDetalles).ListaDeudoresFull();
            default:
                throw new AssertionError();
        }
    }
}
