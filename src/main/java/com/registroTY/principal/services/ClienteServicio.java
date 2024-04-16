/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Cliente;
import com.registroTY.principal.repository.ClienteRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteServicio implements ClienteServicioInterfaz {
    
    @Autowired
    private ClienteRepo repoCliente;
    
    
    @Override
    public List<Cliente> ListaClientes(){
        
        System.out.println("Vamos a ejecutar la consulta...");
        try {
        List<Cliente> listaClientes = (List<Cliente>) repoCliente.findAll();
        return listaClientes;
        } catch (Exception e) {
            System.out.println("Error al encontrar lista de Clientes por: " + e);
            return null;
        }
    }
    
    @Override
    public String GuardarCliente(Cliente cliente){
    
        System.out.println("Guardando cliente...");
        try {
            repoCliente.save(cliente);
            String mensaje =  cliente.getNombre() + " registrado correctamente!";
            return mensaje;
        } catch (Exception e) {
            System.out.println("Error en aplicacion al guardar cliente por: " + e);
            return "Error en aplicación al registrar al cliente!";
        }
    }

    @Override
    public String ConsultarCliente(Cliente cliente) {
        System.out.println("Consultando un cliente...");
        try {
            Optional<Cliente> opcional = repoCliente.findById(cliente.getId()); //Define si se recibe un objeto o null
            if(opcional.isPresent()){ //Si el objeto tiene resultado interno
                return "El cliente ya existe!";
            }else{ //Si el objeto encontrado  está vacío (null)
                return cliente.getNombre(); //Se devuelve el nombre para poder registrar el cliente
            }
        } catch (Exception e) {
            System.out.println("No se encontró cliente por error en base de datos : " + e);
                return "No se encontró cliente por error en aplicación!";
        }
    }
}
