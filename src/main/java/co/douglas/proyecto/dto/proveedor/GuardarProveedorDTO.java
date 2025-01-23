package co.douglas.proyecto.dto.proveedor;

public record GuardarProveedorDTO(
        String correo,
        String direccion,
        int estado,
        String nombre,
        String tipoProveedor
) {}
