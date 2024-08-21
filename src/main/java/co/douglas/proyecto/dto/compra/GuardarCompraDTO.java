package co.douglas.proyecto.dto.compra;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record GuardarCompraDTO(
        @NotBlank(message = "El código no puede estar vacío")
        @Length(max = 100, message = "El código no puede tener más de 100 caracteres")
        String codigo,

        @NotNull(message = "La cantidad en stock no puede ser nula")
        Double cantidadStock,

        @NotNull(message = "El precio no puede ser nulo")
        Double precio,

        @NotNull(message = "La fecha de ingreso no puede ser nula")
        @Past(message = "La fecha de ingreso debe ser una fecha pasada")
        LocalDate fechaIngreso,

        @NotNull(message = "El estado no puede ser nulo")
        Integer estado,

        @NotNull(message = "El ID del producto no puede ser nulo")
        Integer idProducto,

        @NotNull(message = "El ID del proveedor no puede ser nulo")
        Integer idProveedor,

        @NotNull(message = "El ID del usuario no puede ser nulo")
        Integer idUsuario
) {}
