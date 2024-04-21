/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionContable;

import com.registroTY.principal.entities.SalidaItem;
import com.registroTY.principal.entities.Items;
import com.registroTY.principal.services.ItemsServicioInterfaz;
import com.registroTY.principal.services.SalidaItemServicioInterfaz;

/**
 *
 * @author Jhopon
 */
public class RegistroSalidaItem {

   ItemsServicioInterfaz servicioItem;
   SalidaItemServicioInterfaz servicioSalidaItem;
   SalidaItem salidaItem;

   public RegistroSalidaItem(ItemsServicioInterfaz servicioItem, SalidaItemServicioInterfaz servicioSalidaItem, SalidaItem salidaItem) {
      this.servicioItem = servicioItem;
      this.servicioSalidaItem = servicioSalidaItem;
      this.salidaItem = salidaItem;
   }

   public String RegistrarUsoItem() {

      int cantidadUsada = salidaItem.getCantidad();
      int saldoActual = servicioItem.ObtenerSaldoItem(salidaItem.getIdItem());

      if (servicioSalidaItem.GuardarSalidaItem(salidaItem)) {
         if (saldoActual >= 0) {
            int nuevoSaldo = saldoActual - cantidadUsada;
            if (servicioItem.CambiarSaldoItem(nuevoSaldo, salidaItem.getIdItem())) {
               return "Salida y saldo del ítem actualizado correctamente!";
            } else {
               return "Quedó el registro del uso del ítem, pero no se modificó el saldo del mismo!";
            }
         } else {
            System.out.println("Error al obtener els aldo del ítem para su edición, borrando la slida recién registrada...");
            try {
               int ultimoID = servicioSalidaItem.ObtenerUltimoID();
               if (servicioSalidaItem.EliminarRegsitroSalidaItem(ultimoID)) {
                  return "Se borró el registro"
                          + "de salida de ítem al no poder obtener el saldo del mismo!";
               }
            } catch (Exception e) {
               return "Quedó el registro del uso del ítem, pero no se modificó el saldo del mismo!";
            }
         }
      } else {
         return "Error, no se regsitró el uso del ítem ni se modificó el saldo!";
      }
      return null;
   }
}
