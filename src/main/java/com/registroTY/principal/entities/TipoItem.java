/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode
@ToString
public class TipoItem {
    
    private String id;
    private String idEntradaItem;
    private String tipoItem;
    private Date fechaCreacion;
}