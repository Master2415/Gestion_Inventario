package co.douglas.proyecto.dto.usuario;

public record DetalleUsuarioDTO(
        int idUsuario,
        String correo,
        int estado,
        int idEmpleado
) {}
