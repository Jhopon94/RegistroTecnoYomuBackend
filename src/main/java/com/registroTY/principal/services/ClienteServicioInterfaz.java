/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.Cliente;
import java.util.List;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface ClienteServicioInterfaz {
    
    List<Cliente> ListaClientes();
    
    String GuardarCliente(Cliente cliente);
    
    String ConsultarCliente(Cliente cliente);
    
    int ClienteExiste(int id);
    
    int ObtenerServiciosTomados(int id);
    
    List<Cliente> EncontrarClientesPorIDS(List<Integer> ids);
}
