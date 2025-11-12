package com.utn.productos_api.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa la tabla Producto en la base de datos
 */
@Entity // Anota la clase como una entidad JPA
@Data   // Anotación de Lombok para generar getters, setters, toString, etc.
public class Producto {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación autoincremental
    private Long id;

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;

    @Enumerated(EnumType.STRING) // Le dice a JPA que guarde el Enum como su nombre (ej. "ROPA") y no como un número
    private Categoria categoria;
}