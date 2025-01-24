package co.douglas.proyecto.dto.cliente;

public record DetalleClienteDTO(
        int idCliente,
        String cedula,
        String nombre,
        String apellido,
        String direccion,
        String telefono,
        String email,
        Integer puntos,
        int estado
) {}
