/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionUsuarios;

import com.registroTY.principal.entities.Usuario;
import com.registroTY.principal.services.UsuarioServicioInterfaz;

/**
 *
 * @author Jhopon
 */
public class EditarUsuario {
   //Verificar que efectivamente exista para poderlo editar
   
   UsuarioServicioInterfaz servicioUsuario;
   
   public EditarUsuario(UsuarioServicioInterfaz servicioUsuario){
      this.servicioUsuario = servicioUsuario;
   }
   
   public String EditarUsuario(Usuario usuario){
      int verificacion = servicioUsuario.UsuarioExiste(usuario.getIdEmpleado());
      if(verificacion == 1) return servicioUsuario.GuardarUsuario(usuario);
      else if(verificacion == 0) return "El usuario no existe, no se puede editar!";
      else return "Error al editar usuario!";
   }
}
