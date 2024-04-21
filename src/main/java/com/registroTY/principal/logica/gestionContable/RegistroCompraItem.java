/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionContable;

import com.registroTY.principal.entities.EntradaItem;
import com.registroTY.principal.entities.Items;
import com.registroTY.principal.services.EntradaItemServicioInterfaz;
import com.registroTY.principal.services.ItemsServicioInterfaz;

public class RegistroCompraItem {

   public boolean RegistrarPrimeraCompra(EntradaItem entradaItem, EntradaItemServicioInterfaz servicioEntradaItem) {

      if (servicioEntradaItem.GuardarEntradaItem(entradaItem)) {
         return true;
      } else {
         return false;
      }
   }

   public String RegistrarCompraNueva(EntradaItem entradaItem, EntradaItemServicioInterfaz servicioEntradaItem, Items item, ItemsServicioInterfaz servicioItem) {
      //Si se registra correctamente la compra (entradaItem)
      if (servicioEntradaItem.GuardarEntradaItem(entradaItem)) {
         int saldoItem = servicioItem.ObtenerSaldoItem(item.getId());
         //Si se obtuvo un saldo (porque en caso de error manda -1)
         if (saldoItem >= 0) {
            int nuevoSaldo = saldoItem + item.getSaldo();
            System.out.println("Vamos a registrar compra y editar saldo a "  + nuevoSaldo + " en el ítem "  + item.getId());
            //Si se completa la actualziación del saldo del ítem por al compra correctamente
            if (servicioItem.CambiarSaldoItem(nuevoSaldo, item.getId())) {
               return "Registro de compra correcta, y saldo de ítem actualizado!";
            } else {
               System.out.println("Se registró la compra, pero no se actualizó el saldo, se procederá a borrar la compra de la base de datos...");
               if (servicioEntradaItem.BorrarEntradaitem(entradaItem.getId())) {
                  return "El ítem se registró exitosamente, pero no así se actualizó el saldo del ítem, por lo que se procedió a borrar el ítem registrado";
               } else {
                  return "Quedó registrada la compra, pero no se actualizó el saldo del ítem. OJO!";
               }
            }
         } else {
            return "Error de aplicación al obtener el saldo del ítem";
         }
      } else {
         System.out.println("Error al registrar la entrada ítem y modificar el saldo del ítem!");
         return "Error en registrar la compra y actualizar el saldo del ítem. No se hizo nada!";
      }
   }
}
