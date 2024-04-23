/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionClientes;

import com.registroTY.principal.entities.Cliente;
import com.registroTY.principal.services.ClienteServicioInterfaz;

/**
 *
 * @author Jhopon
 */
public class EdicionCliente {

   ClienteServicioInterfaz servicioCliente;

   public EdicionCliente(ClienteServicioInterfaz servicioCliente) {

      this.servicioCliente = servicioCliente;
   }

   public String EditarCliente(Cliente cliente) {

      int verificacion = servicioCliente.ClienteExiste(cliente.getId());
      if (verificacion == 1) {
         try {
            int servTomados = servicioCliente.ObtenerServiciosTomados(cliente.getId());
            if (servTomados < 0) {
               return "Error al obtener los servicios tomados del cliente...";
            } else {
               cliente.setServiciosTomados(servTomados);
               return servicioCliente.GuardarCliente(cliente);
            }
         } catch (Exception e) {
         }
      } else if (verificacion == 0) {
         return "Cliente no existe, no hay nada que editar!";
      } else {
         return "Error al Verificar existencia del cliente";
      }
      return "";
   }
}
