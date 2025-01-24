package co.douglas.proyecto.dto.cliente;

public record ItemClienteDTO(
        int idCliente,
        String nombre,
        String apellido,
        String cedula
) {}