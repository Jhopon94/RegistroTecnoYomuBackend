/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Cliente;
import com.registroTY.principal.services.ClienteServicioInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//////Controlador Principal del ojeto Cliente////////////////////
@RestController
public class ClienteController {
    
    @Autowired
    private ClienteServicioInterfaz servicioCliente;
    
    @GetMapping("/Clientes")
    public List<Cliente> ListaClientes(){
        
        return servicioCliente.ListaClientes();
    }
    
    @PostMapping("/Clientes")
    public void GuardarCliente(@RequestBody Cliente cliente){
    
        servicioCliente.GuardarCliente(cliente);
    }
}
