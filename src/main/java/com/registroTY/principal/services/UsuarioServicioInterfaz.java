/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

import com.registroTY.principal.entities.Empleado;
import com.registroTY.principal.entities.Usuario;
import java.util.List;

///////Este interfaz es solo para que se vea mas ordenado y bonito ////////////
public interface UsuarioServicioInterfaz {
    
    List<Usuario> ListaUsuarios();
    
    String EliminarUsuario(int id);
    
    String GuardarUsuario(Usuario usuario);
    
    String ConsultarUsuarioExiste(Usuario usuario);
    
    int UsuarioExiste(int idEmpleado);
    
}
