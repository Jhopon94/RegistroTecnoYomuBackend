/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Immutable //Para que no se pueda modificar una vez creado
@Entity
@Table(name = "salidaItem")
@Getter @Setter
@EqualsAndHashCode
@ToString
public class SalidaItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message="Debe especificarse el id del equipo donde se usó el ítem!")
    @Positive
    private Integer idEquipo;
    @NotNull(message="Debe especificarse el id del ítem en cuestión!")
    @Positive
    private Integer idItem;
    @NotNull(message="Debe especificarse la cantidad de tiems que salen!")
    @PositiveOrZero
    private Integer cantidad;
    private Date fechaUso;
}

