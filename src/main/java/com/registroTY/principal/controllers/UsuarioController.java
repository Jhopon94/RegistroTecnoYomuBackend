/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.controllers;

import com.registroTY.principal.entities.Usuario;
import com.registroTY.principal.logica.gestionUsuarios.RegistroUsuario;
import com.registroTY.principal.services.UsuarioServicioInterfaz;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @PutMapping("/Usuarios")
    public String ActualizarUsuario(@Valid @RequestBody Usuario usuario, BindingResult resultado){
        if(resultado.hasErrors()) return "Error al actualziar suuario, revisa que los datos sean correctos!";
        else return servicioUsuario.GuardarUsuario(usuario);
    }
    
    @PostMapping("/Usuarios")
    public String GuardarUsuario(@Valid @RequestBody Usuario usuario, BindingResult resultado){
    
        if(resultado.hasErrors()){
            return "Hay algún dato incorrecto, recuerda que el nombre de usaurio no debe llevar espacios!";
        }else{
            RegistroUsuario registrarUsuario = new RegistroUsuario(usuario, servicioUsuario);
            return registrarUsuario.RegistrarUsuario();
        }
    }
    
    @DeleteMapping("/Usuarios/{id}")
    public String EliminarUsuario(@PathVariable int id){
    
        return servicioUsuario.EliminarUsuario(id);
    }
}
