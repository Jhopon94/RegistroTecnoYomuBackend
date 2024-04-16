/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionEquipos;

import com.registroTY.principal.entities.Detalles;
import com.registroTY.principal.entities.Equipo;
import com.registroTY.principal.services.DetallesServicioInterfaz;
import com.registroTY.principal.services.EquipoServicioInterfaz;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jhopon
 */
public class RegistroEquipo {

    private Equipo equipo;
    private List<Detalles> detalles;
    private EquipoServicioInterfaz servicioEquipo;
    private DetallesServicioInterfaz servicioDetalles;
    private Object[] listaObjetos;

    ///// Constructor/////////////
    public RegistroEquipo(Object[] contenedorObjetos, EquipoServicioInterfaz servicioEquipo, DetallesServicioInterfaz servicioDetalles) {
        detalles = new ArrayList<>();
        equipo = new Equipo();
        this.servicioEquipo = servicioEquipo;
        this.servicioDetalles = servicioDetalles;
        listaObjetos = contenedorObjetos;
    }

    //Deevuelve un objeto que contiene mensaje de proceso y boolean de exito en operación!
    public ProcesoRegistroImpl RegistrarEquipo() {
        IdentificarObjetos(listaObjetos);
        ProcesoRegistroImpl resultado = new ProcesoRegistroImpl();
        ProcesoRegistroImpl resultadoEquipo = servicioEquipo.GuardarEquipo(equipo);
        ProcesoRegistroImpl resultadoDetalles = servicioDetalles.GuardarVariosDetalles(detalles);
        resultado.setMensaje(resultadoEquipo + " y " + resultadoDetalles);
        if(resultadoEquipo.getProcesoExitoso() && resultadoDetalles.getProcesoExitoso()) resultado.setProcesoExitoso(true);
        else resultado.setProcesoExitoso(false);
        return resultado;
    }

    private void IdentificarObjetos(Object[] listaObjetos) {

        for (Object objeto : listaObjetos) {
            if (objeto instanceof Map) { //Si el objeto se puede mapear
                Map<String, Object> objetoConvertido = (Map<String, Object>) objeto; //Se convierte en Map cada objeto
                String tipo = (String) objetoConvertido.get("tipo");
                switch (tipo) {

                    case "detalles" -> {
                        //Encontramos el array de detalles y vamos a hacerle casting a array list que contiene MAPS primero
                        //Se verifica que si sea convertible a ArrayList
                        if(objetoConvertido.get("detalles") instanceof ArrayList){
                            //Una vez Verificado
                            ArrayList<Map<String, Object>> listaAuxiliar = (ArrayList<Map<String, Object>>) objetoConvertido.get("detalles");
                            //Convertimos cada Map en un objeto Detalles
                            for(Map<String, Object> objetoMap : listaAuxiliar){
                                //Creamos un objeto detalle auxiliar en cada repaso para agregarlo a la lista de Detalles
                                //incializada en el constructor
                                Detalles detalleAux = new Detalles();
                                detalleAux.setDescripcion((String)objetoMap.get("descripcion"));
                                detalleAux.setIdEquipo((int) objetoMap.get("idEquipo"));
                                detalleAux.setPrecio((int) objetoMap.get("precio"));
                                //Una vez creado el objeto, lo agregamos a la lista
                                detalles.add(detalleAux);
                            }
                            System.out.println("Convertido a lista de Detalles...");
                            System.out.println("verificamos con: " + detalles.get(0).getDescripcion());
                        }
                        
                    }

                    case "equipo" -> {
                        equipo.setIdCliente((int) objetoConvertido.get("idCliente"));
                        equipo.setTipoIngreso((String) objetoConvertido.get("tipoIngreso"));
                        equipo.setModelo((String) objetoConvertido.get("modelo"));
                        equipo.setCondicionesFisicasRecibidas((String) objetoConvertido.get("condicionesFisicasRecibidas"));
                        equipo.setDaniosRecibido((String) objetoConvertido.get("daniosRecibido"));
                        equipo.setPartesInternasRecibido((String) objetoConvertido.get("partesInternasRecibido"));
                        equipo.setEstadoEquipo((String) objetoConvertido.get("estadoEquipo"));
                        equipo.setDiasGarantia((int) objetoConvertido.get("diasGarantia"));
                        equipo.setPrecioTotal((int) objetoConvertido.get("precioTotal"));
                        equipo.setSaldoPendiente((int) objetoConvertido.get("saldoPendiente"));
                    }
                }
            }
        }
    }

}
