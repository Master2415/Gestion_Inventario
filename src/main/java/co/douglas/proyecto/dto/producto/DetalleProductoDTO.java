package co.douglas.proyecto.dto.producto;

import co.douglas.proyecto.modelo.enumeraciones.TipoProducto;

public record DetalleProductoDTO(
        int idProducto,
        String codigo,
        int estado,
        double iva,
        String nombre,
        double precioNeto,
        double precioTotal,
        double stock,
        TipoProducto tipoProducto,
        double utilidad
) {}
