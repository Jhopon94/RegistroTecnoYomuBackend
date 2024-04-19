/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.logica.gestionContable.ConsultaIngresos;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatosContablesController {

    @Autowired
    private DetallesServicioInterfaz servicioDetalles;

    @GetMapping("/DatosContables/{peticionLista}/{fechaInicioString}/{fechaFinString}")
    public List<?> ListaRequerida(@PathVariable String peticionLista, @PathVariable String fechaInicioString, @PathVariable String fechaFinString) {
        switch (peticionLista) {
            case "ingresos":
                //Si se piden ingresos, se intenta parsear las fechas
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaInicio = null;
                Date fechaFin = null;
                
                try {
                    System.out.println("Intentaremos parsear las fechas...");
                    fechaInicio = dateFormat.parse(fechaInicioString);
                    fechaFin = dateFormat.parse(fechaFinString);
                    System.out.println("Fechas parseadas correctamente!");
                    return new ConsultaIngresos(fechaInicio, fechaFin, servicioDetalles).listaIngresos();
                } catch (ParseException e) {
                    System.out.println("No se pudiero parsear las fechas entrantes por: " + e);
                    return null;
                }
            case "compras":
                return null;
            case "deudores":
                return null;
            default:
                throw new AssertionError();
        }
    }
}
