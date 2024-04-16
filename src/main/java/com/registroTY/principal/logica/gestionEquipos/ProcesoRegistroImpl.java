/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica.gestionEquipos;

/**
 *
 * @author Jhopon
 */
public class ProcesoRegistroImpl implements ProcesoRegistroEquipoInterface{
    
    private String mensaje;
    private boolean procesoExitoso;

    @Override
    public String getMensaje() {
        return mensaje;
    }

    @Override
    public boolean getProcesoExitoso() {
        return procesoExitoso;
    }
    
    @Override
    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }
    
    @Override
    public void setProcesoExitoso(boolean bandera){
        this.procesoExitoso = bandera;
    }
}
