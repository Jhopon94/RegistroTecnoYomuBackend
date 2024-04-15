/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.repository.DetallesRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetallesServicio implements DetallesServicioInterfaz {
    
    @Autowired
    private DetallesRepo repoDetalles;
    
    @Override
    public List<Detalles> ListaDetalles(){
        
        System.out.println("Ejecutando consulta de detalles...");
        
        try {
        List<Detalles> listaDetalles = (List<Detalles>) repoDetalles.findAll();
        return listaDetalles;
        } catch (Exception e) {
            System.out.println("Error en la consutal de detalles por: " + e);
            return null;
        }
    }

    @Override
    public void EliminarDetalle(int id) {
        
        System.out.println("Eliminando detalle...");
        try {
            repoDetalles.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar detalle por: " + e);
        }
    }

    @Override
    public void GuardarDetalle(Detalles detalle) {
        
        System.out.println("Guardando detalle...");
        try {
        repoDetalles.save(detalle);
        } catch (Exception e) {
            System.out.println("Error al guardar detalle por: " + e);
        }
    }

    @Override
    public void GuardarVariosDetalles(List<Detalles> detalles) {
           System.out.println("Guardando varios detalles...");
           try {
        repoDetalles.saveAll(detalles);
        } catch (Exception e) {
               System.out.println("Erro al guardar lista de detalles por: " + e);
        }
    }
}
