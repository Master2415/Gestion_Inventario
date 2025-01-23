package co.douglas.proyecto.dto.proveedor;

public record DetalleProveedorDTO(
        int idProveedor,
        String correo,
        String direccion,
        int estado,
        String nombre,
        String tipoProveedor
) {}

