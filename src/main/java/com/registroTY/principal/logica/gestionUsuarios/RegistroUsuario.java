/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionUsuarios;

import com.registroTY.principal.entities.Usuario;
import com.registroTY.principal.services.UsuarioServicioInterfaz;

public class RegistroUsuario {
    
    private Usuario usuario;
    private UsuarioServicioInterfaz servicioUsuario;
    private String mensaje;
    
    public RegistroUsuario(Usuario usuario, UsuarioServicioInterfaz servicioUsuario){
        this.usuario = usuario;
        this.servicioUsuario = servicioUsuario;
    }
    
    
    public String RegistrarUsuario(){
        
        //Primero checamos que no exista
        if(!UsuarioExiste()) return servicioUsuario.GuardarUsuario(usuario);
        else return mensaje;
    }
    
    //Para notificar al usuario en caso de que el usuario exista en la base de datos
    private boolean UsuarioExiste(){
        mensaje = servicioUsuario.ConsultarUsuarioExiste(usuario);
        //si usuario no existe, es decir, se devolvió el nombre
        return !usuario.getNombreUsuario().equals(mensaje);
    }
}
