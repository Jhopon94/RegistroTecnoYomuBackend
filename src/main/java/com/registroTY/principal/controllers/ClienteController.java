/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Cliente;
import com.registroTY.principal.logica.gestionClientes.EdicionCliente;
import com.registroTY.principal.logica.gestionClientes.RegistroCliente;
import com.registroTY.principal.services.ClienteServicioInterfaz;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal del ojeto Cliente////////////////////
@RestController
public class ClienteController {

   @Autowired
   private ClienteServicioInterfaz servicioCliente;

   @GetMapping("/Clientes")
   public List<Cliente> ListaClientes() {

      return servicioCliente.ListaClientes();
   }

   @PostMapping("/Clientes")
   public String GuardarCliente(@Valid @RequestBody Cliente cliente, BindingResult resultado) {
      //Este string spring lo envía automáticamente como respuesta al front

      if (resultado.hasErrors()) {
         return "Algún dato del cliente está malo";
      } else {
         RegistroCliente registrarCliente = new RegistroCliente(cliente, servicioCliente);
         return registrarCliente.RegistrarCliente();
      }
   }

   @PutMapping("/Clientes")
   public String ActualizarCliente(@Valid @RequestBody Cliente cliente, BindingResult resultado) {
      if (resultado.hasErrors()) {
         return "Error al actualizar el cliente, revisa los datos ingresados!";
      } else {
         return new EdicionCliente(servicioCliente).EditarCliente(cliente);
      }
   }
}
