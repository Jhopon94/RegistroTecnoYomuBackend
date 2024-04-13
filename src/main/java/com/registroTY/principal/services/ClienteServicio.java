/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Cliente;
import com.registroTY.principal.repository.ClienteRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteServicio implements ClienteServicioInterfaz {
    
    @Autowired
    private ClienteRepo repoCliente;
    
    @Override
    public List<Cliente> ListaClientes(){
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<Cliente> listaClientes = (List<Cliente>) repoCliente.findAll();
        return listaClientes;
    }
    
    @Override
    public void GuardarCliente(Cliente cliente){
    
        repoCliente.save(cliente);
    }
}
