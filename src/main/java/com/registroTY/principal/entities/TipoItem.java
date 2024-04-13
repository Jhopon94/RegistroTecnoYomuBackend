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
import org.hibernate.annotations.Immutable;

@Immutable //Para que no se pueda modificar una vez creado
@Entity
@Table(name = "tipoItem")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class TipoItem {
    
    @Id
    private int id;
    private int idEntradaItem;
    private String tipoDeItem;
    private Date fechaCreacion;
}