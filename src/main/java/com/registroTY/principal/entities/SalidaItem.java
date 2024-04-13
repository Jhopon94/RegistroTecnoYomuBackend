/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "salidaItem")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class SalidaItem {
    
    @Id
    private String id;
    private String idEquipo;
    private String idItem;
    private String descripcion;
    private int cantidad;
    private Date fechaUso;
}

