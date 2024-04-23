/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal del ojeto Detalles////////////////////
@RestController
public class DetallesController {

    @Autowired
    private DetallesServicioInterfaz servicioDetalles;

    @GetMapping("/Detalles/{idEquipo}")
    public List<Detalles> ListaDetallesPorId(@PathVariable String idEquipo) {

        return servicioDetalles.ListaDetalles(idEquipo);
    }

    @PostMapping("/Detalles")
    public void GuardarDetalle(@RequestBody List<Detalles> detalles) {

            System.out.println("Es una lista de detalles...");
            servicioDetalles.GuardarVariosDetalles(detalles);
    }

    @DeleteMapping("/Detalles/{id}")
    public void EliminarDetalle(@PathVariable int id) {

        servicioDetalles.EliminarDetalle(id);
    }
}
