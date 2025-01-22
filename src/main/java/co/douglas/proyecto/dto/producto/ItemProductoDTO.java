package co.douglas.proyecto.dto.producto;

public record ItemProductoDTO(
        String codigo,
        String nombre,
        double stock,
        double precioTotal
) {}
