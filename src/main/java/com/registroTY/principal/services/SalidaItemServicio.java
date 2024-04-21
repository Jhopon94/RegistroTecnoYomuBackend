/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////
import com.registroTY.principal.entities.SalidaItem;
import com.registroTY.principal.repository.SalidaItemRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalidaItemServicio implements SalidaItemServicioInterfaz {

   @Autowired
   private SalidaItemRepo repoSalidaItem;

   @Override
   public List<SalidaItem> ListaSalidasItem() {

      System.out.println("Consultando lista de las salidas de items...");
      try {
         List<SalidaItem> listaSalidasItem = (List<SalidaItem>) repoSalidaItem.findAll();
         return listaSalidasItem;
      } catch (Exception e) {
         System.out.println("Error al consultar lista de las salidas de ítems por: " + e);
         return null;
      }
   }

   @Override
   public boolean GuardarSalidaItem(SalidaItem salidaItem) {

      System.out.println("Guardando salida de ítem...");
      try {
         repoSalidaItem.save(salidaItem);
         return true;
      } catch (Exception e) {
         System.out.println("Error al guardar salida de ítem por: " + e);
         return false;
      }
   }

   @Override
   public int UltimaSalidaItem() {
      System.out.println("Consultando la última salida de ítem...");
      try {
         if (repoSalidaItem.UltimaSalidaItem().isPresent()) {
            SalidaItem salidaItem = repoSalidaItem.UltimaSalidaItem().get();
            System.out.println("Se obtuvo el ultimo registro de salida ítem satisfactoriamente!");
            return salidaItem.getId();
         } else {
            System.out.println("No hay registros en la tabla de salida item...");
            return -1;
         }
      } catch (Exception e) {
         System.out.println("No se pudo obtener la última salida de ítem por: " + e);
         return -2;
      }
   }
   
   public boolean EliminarRegsitroSalidaItem(int id){
      System.out.println("Eliminando el registro del uso del ítem!");
      try {
         repoSalidaItem.deleteById(id);
         return true;
      } catch (Exception e) {
         System.out.println("rror al borrar el registro de la salida del ítem!");
         return false;
      }
   }
   @Override
   public int ObtenerUltimoID(){
      System.out.println("Obteniendo el último id de la salidaItem ...");
      try {
         return repoSalidaItem.UltimoIdRegistrado();
      } catch (Exception e) {
         System.out.println("Error al obtener el último id registrado!");
         return -1;
      }
   }
}
