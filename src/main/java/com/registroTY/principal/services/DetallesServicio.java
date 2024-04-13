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
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<Detalles> listaDetalles = (List<Detalles>) repoDetalles.findAll();
        return listaDetalles;
    }

    @Override
    public void EliminarDetalle(int id) {
        repoDetalles.deleteById(id);
    }

    @Override
    public void GuardarDetalle(Detalles detalle) {
        repoDetalles.save(detalle);
    }

    @Override
    public void GuardarVariosDetalles(List<Detalles> detalles) {
        
        repoDetalles.saveAll(detalles);
    }
}
