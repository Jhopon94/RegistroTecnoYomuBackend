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
import org.springframework.dao.DataIntegrityViolationException;
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
    public String GuardarUsuario(Usuario usuario){
        System.out.println("Guardando usuario...");
        try {
        repoUsuario.save(usuario);
        return usuario.getNombreUsuario() +  " registrado satisfactoriamente como usuario!";
        }catch(DataIntegrityViolationException pe){
            
            System.out.println("Error constraint por: " + pe);
            return "Error, probablemente ya se usó este nombre de usuario!";
            
        } catch (Exception e) {
            System.out.println("No se registró por: " + e);
            return "No se pudo regisrar el usuario por problemas en la aplicación";
        }
    }
    
    // La consulta de usuario no puede ser por id porque se genera automáticamente, mas bien por idEmpleado
    @Override
    public String ConsultarUsuarioExiste(Usuario usuario) {
        System.out.println("Consultando la existencia de un usuario...");
        try {
            if(repoUsuario.existsById(usuario.getIdEmpleado())){ //Si el objeto tiene resultado interno
                return "El empleado ya tiene un usuario llamado " + usuario.getNombreUsuario() ;
            }else{ //Si el objeto encontrado  está vacío (null)
                return usuario.getNombreUsuario(); //Se devuelve el nombre para poder registrar el usuario
            }
        } catch (Exception e) {
            System.out.println("No se encontró usuario por error en base de datos : " + e);
            return "No se encontró usuario por error en aplicación";
        }
    }
}
