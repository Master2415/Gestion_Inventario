package co.douglas.proyecto.dto.usuario;

public record GuardarUsuarioDTO(
        String correo,
        String contrasena,
        int estado,
        int idEmpleado
) {}
