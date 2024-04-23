/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionClientes;

import com.registroTY.principal.entities.Cliente;
import com.registroTY.principal.services.ClienteServicioInterfaz;

public class RegistroCliente {
   
   private Cliente cliente;
   private ClienteServicioInterfaz servicioCliente;
   private String mensaje;
   
   public RegistroCliente(Cliente cliente, ClienteServicioInterfaz servicioCliente) {
      this.cliente = cliente;
      this.servicioCliente = servicioCliente;
   }
   
   public String RegistrarCliente() {

      //Ponemos 0 servicios tomados ya que es cliente nuevo.
      cliente.setServiciosTomados(0);
      //Primero checamos que no exista
      if (!ClienteExiste()) {
         return servicioCliente.GuardarCliente(cliente);
      } else {
         return mensaje;
      }
   }

   //Para notificar al usuario en caso de que el cliente exista en la base de datos
   private boolean ClienteExiste() {
      mensaje = servicioCliente.ConsultarCliente(cliente);
      //si cliente no existe, es decir, se devolvió el nombre
      return !cliente.getNombre().equals(mensaje);
   }
}
