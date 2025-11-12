package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "DTO para crear o actualizar un producto") //
public class ProductoDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre del producto", example = "Teclado Mecánico RGB", requiredMode = Schema.RequiredMode.REQUIRED) //
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Schema(description = "Descripción detallada del producto", example = "Teclado con switches Cherry MX Red")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Schema(description = "Precio del producto", example = "120.50", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double precio;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Schema(description = "Cantidad en stock del producto", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer stock;

    @NotNull(message = "La categoría no puede ser nula")
    @Schema(description = "Categoría del producto", example = "ELECTRONICA", requiredMode = Schema.RequiredMode.REQUIRED)
    private Categoria categoria;
}
