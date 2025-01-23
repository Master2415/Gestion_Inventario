package co.douglas.proyecto.servicios.interfaces;

import co.douglas.proyecto.dto.proveedor.DetalleProveedorDTO;
import co.douglas.proyecto.dto.proveedor.GuardarProveedorDTO;

import java.util.List;

public interface ProveedorServicio {

    // Crear un proveedor
    DetalleProveedorDTO guardarProveedor(GuardarProveedorDTO proveedorDTO) throws Exception;

    // Actualizar un proveedor
    DetalleProveedorDTO modificarProveedor(int idProveedor, GuardarProveedorDTO proveedorDTO) throws Exception;

    // Eliminar un proveedor
    void eliminarProveedor(int idProveedor) throws Exception;

    // Obtener detalles de un proveedor por ID
    DetalleProveedorDTO detalleProveedor(int idProveedor) throws Exception;

    // Listar todos los proveedores
    List<DetalleProveedorDTO> listarProveedores();

    // Listar proveedores por estado (ejemplo: activos/inactivos)
    List<DetalleProveedorDTO> listarProveedoresPorEstado(int estado);

    // Listar proveedores por tipo
    List<DetalleProveedorDTO> listarProveedoresPorTipo(String tipo);
}
