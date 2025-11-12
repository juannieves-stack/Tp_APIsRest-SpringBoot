package com.utn.productos_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO para actualizar parcialmente el stock de un producto")
public class ActualizarStockDTO {

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Schema(description = "La nueva cantidad de stock", example = "45", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer stock;
}
