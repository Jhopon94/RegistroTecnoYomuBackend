/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.services;

/////////////// Aquí va la lógica de Negocio ///////////////////
import com.registroTY.principal.entities.TipoItem;
import com.registroTY.principal.repository.TipoItemRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoItemServicio implements TipoItemServicioInterfaz {

   @Autowired
   private TipoItemRepo repoTipoItem;

   @Override
   public List<TipoItem> ListaTiposItem() {

      System.out.println("Consultando lista de ítems...");
      try {
         List<TipoItem> listaTiposItem = (List<TipoItem>) repoTipoItem.findAll();
         return listaTiposItem;
      } catch (Exception e) {
         System.out.println("Error al consultar lista de tipos de ítem por: " + e);
         return null;
      }
   }

   @Override
   public String GuardarTipoItem(TipoItem tipoItem) {
      System.out.println("Guardando tipo de item...");
      try {
         repoTipoItem.save(tipoItem);
         return "Tipo de ítem " + tipoItem.getTipoDeItem() + " registrado correctamente!";
      } catch (Exception e) {
         System.out.println("Error al guardar tipo de ítem por: " + e);
         return "Error de aplicación al REGISTRAR el tipo de ítem";
      }
   }

   @Override
   public String ConsultarExistenciaTipoItem(TipoItem tipoItem) {
      System.out.println("Vamos a verificar que este tipo de ítem no esté registrado...");
      try {
         if (repoTipoItem.existsByTipoDeItem(tipoItem.getTipoDeItem())) {
            System.out.println("Ya existe el tipo de ítem");
            return "Ya existe este tipo de ítem";
         } else {
            System.out.println("No existe este tipo de ítem... se puede registrar!");
            return tipoItem.getTipoDeItem();
         }
      } catch (Exception e) {
         System.out.println("No se registró el tipo de Item por: " + e);
         return "Error de aplicación al consultare el tipo de ítem";
      }
   }

   @Override
   public String ConsultarUltimoTipoItem() {
      System.out.println("Adquieriendo el último tipo de ítem...");
      try {
         if (repoTipoItem.UltimoTipoItem().isPresent()) {
            TipoItem tipoItem = repoTipoItem.UltimoTipoItem().get();
            System.out.println("Obtenido el último registro del tipoItem!");
            return tipoItem.getId();
         }else{
            System.out.println("No hay registros en la tabla de tipoItem!");
            return "";
         }
      } catch (Exception e) {
         System.out.println("No se pudo obtener el último ipo de item por: " + e);
         return null;
      }
   }
}
