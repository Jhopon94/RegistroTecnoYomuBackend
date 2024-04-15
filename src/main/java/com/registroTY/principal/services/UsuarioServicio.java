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
        
        System.out.println("Consultando lista de usuarios...");
        try {
        List<Usuario> listaUsuarios = (List<Usuario>) repoUsuario.findAll();
        return listaUsuarios;
        } catch (Exception e) {
            System.out.println("Error al obtener lista de usuarios por: " + e);
            return null;
        }
    }

    @Override
    public void EliminarUsuario(int id) {
        System.out.println("Eliminando usuario...");
        try {
            
        repoUsuario.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario por: " + e);
        }
    }
    
    @Override
    public void GuardarUsuario(Usuario usuario){
        System.out.println("Guardando usuario...");
        try {
        repoUsuario.save(usuario);
        } catch (Exception e) {
            System.out.println("");
        }
    }
}
