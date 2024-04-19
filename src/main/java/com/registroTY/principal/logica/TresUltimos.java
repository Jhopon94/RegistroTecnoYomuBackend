/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.logica;

/**
 *
 * @author Jhopon
 */
public class TresUltimos {
    
    public String IdNuevo(String texto){
    
        int longitud = texto.length();
        String palabraSinNum = texto.substring(0, longitud - 3);
        String numeroCadena = texto.substring(longitud - 3, longitud);
        int numero = Integer.parseInt(numeroCadena);
        numero++;
        if(("" + numero).length() == 1  )return palabraSinNum + "00" + numero;
        if(("" + numero).length() == 2  )return palabraSinNum + "0" + numero;
        else return palabraSinNum + numero;
    }
}
