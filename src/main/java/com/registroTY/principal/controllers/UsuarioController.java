/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Usuario;
import com.registroTY.principal.services.UsuarioServicioInterfaz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//////Controlador Principal del ojeto Usuario////////////////////
@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioServicioInterfaz servicioUsuario;
    
    @GetMapping("/Usuarios")
    public List<Usuario> ListaUsuario(){
        
        return servicioUsuario.ListaUsuarios();
    }
    
    @PostMapping("/Usuarios")
    public void GuardarUsuario(@RequestBody Usuario usuario){
    
        servicioUsuario.GuardarUsuario(usuario);
    }
    
    @DeleteMapping("/Usuarios/{id}")
    public void EliminarUsuario(@PathVariable int id){
    
        servicioUsuario.EliminarUsuario(id);
    }
}
