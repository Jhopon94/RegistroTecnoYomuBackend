/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Usuario;
import com.registroTY.principal.repository.UsuarioRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioServicio implements UsuarioServicioInterfaz {
    
    @Autowired
    private UsuarioRepo repoUsuario;
    
    @Override
    public List<Usuario> ListaUsuarios(){
        
        System.out.println("Vamos a ejecutar la consulta...");
        List<Usuario> listaUsuarios = (List<Usuario>) repoUsuario.findAll();
        return listaUsuarios;
    }
}
