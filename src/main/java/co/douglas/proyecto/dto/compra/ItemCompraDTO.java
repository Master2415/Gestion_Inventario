package co.douglas.proyecto.dto.compra;

import jakarta.validation.constraints.NotNull;

public record ItemCompraDTO(

        @NotNull(message = "La cantidad en stock no puede ser nula")
        Double cantidadStock,

        @NotNull(message = "El precio no puede ser nulo")
        Double precio,

        @NotNull(message = "El estado no puede ser nulo")
        Integer estado
) {}
