package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para mostrar la información de un producto (incluye ID)")
public class ProductoResponseDTO {

    @Schema(description = "ID único del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Teclado Mecánico RGB")
    private String nombre;

    @Schema(description = "Descripción detallada del producto", example = "Teclado con switches Cherry MX Red")
    private String descripcion;

    @Schema(description = "Precio del producto", example = "120.50")
    private Double precio;

    @Schema(description = "Cantidad en stock del producto", example = "50")
    private Integer stock;

    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;
}
