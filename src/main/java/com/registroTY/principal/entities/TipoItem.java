/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registroTY.principal.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message="Debe especificarse el id de la entrada del ítem!")
    @Positive
    private Integer idEntradaItem;
    @NotBlank(message="Debe especificarse el tipo de item!")
    @Column(unique = true)
    @Pattern(regexp = "\\S+", message = "El nombre de uusario no debe llevar espacios")
    private String tipoDeItem;
    private Date fechaCreacion;
}