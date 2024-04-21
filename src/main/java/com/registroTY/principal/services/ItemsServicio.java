/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////

import com.registroTY.principal.entities.Items;
import com.registroTY.principal.repository.ItemsRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemsServicio implements ItemsServicioInterfaz {
    
    @Autowired
    private ItemsRepo repoItems;
    
    @Override
    public List<Items> ListaItems(){
        
        System.out.println("consultando lista de ítems...");
        try {
        List<Items> listaItems = (List<Items>) repoItems.findAll();
        return listaItems;
        } catch (Exception e) {
            System.out.println("Error al consultar lita de ítems por: " + e);
            return null;
        }
    }

    @Override
    public boolean GuardarItem(Items item) {
        
        System.out.println("Guardando ítem...");
        try {
        repoItems.save(item);
        return true;
        } catch (Exception e) {
            System.out.println("Error al guardar ítem por: " + e);
            return false;
        }
    }
    
    @Override
    public String ConsultarExistenciaItem(Items item){
    
        System.out.println("Conusltando existencia de item con nombre: " + item.getNombre());
        try {
           if(repoItems.existsByNombre(item.getNombre())){
                return "Ya existe el ítem!";
            }else{
                return item.getNombre();
            }
        } catch (Exception e) {
            System.out.println("No se encontró item por: " + e);
            return "No se pudo verificar la existencia del ítem por error en la aplicación";
        }
    }
    
    @Override
    public String findUltimoItem(){
       System.out.println("Consultando el último ítem registrado!...");
       try {
          Items item = new Items();
          if(repoItems.UltimoItem().isPresent()){
             item = repoItems.UltimoItem().get();
             System.out.println("Adquirido el último item");
             return item.getId();
          }else{
               System.out.println("No hay registros...");
               return "";
          }
       } catch (Exception e) {
          System.out.println("No se pudo obtener el último ítem por error de la aplciación: " + e);
          return null;
       }
    }
    
    @Override
    public boolean BorrarItem(String id){
       System.out.println("Se borrará un ítem por falla en el registro de su compra!");
       try {
          repoItems.deleteById(id);
          return true;
       } catch (Exception e) {
          System.out.println("Error al borrar ítem por: " + e);
          return false;
       }
    }
    
    @Override
    public int ObtenerSaldoItem(String id){
       System.out.println("Vamos a obtener el saldo del ítem...");
       try {
          return repoItems.ObtenerSaldoItem(id);
       } catch (Exception e) {
          System.out.println("No se pudo obtener el saldo del ítem por: " + e);
          return -1;
       }
    }
    
    @Override
    public boolean CambiarSaldoItem(int saldoNuevo, String id){
       System.out.println("Actualizando el saldo del ítem en cuestión...");
       try {
            int respuesta = repoItems.NuevoSaldo(saldoNuevo, id); 
             System.out.println("Se cambió el saldo satisfactoriamente en repo, y devolvió " + respuesta);
             return true;
       } catch (Exception e) {
          System.out.println("Error al actualizar el saldo del ítem en cuestión por " + e);
          return false;
       }
    }
    
    @Override
    public boolean EliminarRegistroCompra(String id){
       System.out.println("Vamos a eliminar el registro de compra...");
       try {
          repoItems.deleteById(id);
          return true;
       } catch (Exception e) {
          System.out.println("Error al eliminar el registro de la compra por " + e);
          return false;
       }
    }
}
