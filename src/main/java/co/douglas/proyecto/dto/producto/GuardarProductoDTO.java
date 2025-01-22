package co.douglas.proyecto.dto.producto;

import co.douglas.proyecto.modelo.enumeraciones.TipoProducto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuardarProductoDTO(
        @NotBlank String codigo,
        @NotBlank String nombre,
        @NotNull TipoProducto tipoProducto,
        @NotNull double utilidad,
        @NotNull double iva,
        @NotNull double precioTotal,
        @NotNull double stock,
        @NotNull double precioNeto,
        @NotNull int estado
) {}
