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
            if(repoCliente.existsById(cliente.getId())){ //Si el objeto tiene resultado interno
                return "El cliente ya existe!";
            }else{ //Si el objeto encontrado  está vacío (null)
                return cliente.getNombre(); //Se devuelve el nombre para poder registrar el cliente
            }
        } catch (Exception e) {
            System.out.println("No se encontró cliente por error en base de datos : " + e);
                return "No se encontró cliente por error en aplicación!";
        }
    }
    
    @Override
    public int ClienteExiste(int id){
       System.out.println("Buscando si el cliente en cuestión existe!");
       try {
          if(repoCliente.existsById(id)) return 1;
          else return 0;
       } catch (Exception e) {
          System.out.println("Error al verificar la existencia del cliente por: " + e);
          return -1;
       }
    }
    
    @Override
    public int ObtenerServiciosTomados(int id){
       System.out.println("Obteniendo servicios tomados del cliente...");
       try {
          return repoCliente.ServiciosTomados(id);
       } catch (Exception e) {
          System.out.println("Error al obtener los servicios tomados del cliente por: " + e);
          return -1;
       }
    }
    
    @Override
    public List<Cliente> EncontrarClientesPorIDS(List<Integer> ids){
       System.out.println("Encontrando lista de clientes por id...");
       try {
          return repoCliente.findByIdIn(ids);
       } catch (Exception e) {
          System.out.println("No se pudo obtener lsita de clientes por id por: " + e);
          return null;
       }
    }
}
