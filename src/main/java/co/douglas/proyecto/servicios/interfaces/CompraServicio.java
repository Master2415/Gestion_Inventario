package co.douglas.proyecto.servicios.interfaces;

import co.douglas.proyecto.dto.compra.DetalleCompraDTO;
import co.douglas.proyecto.dto.compra.GuardarCompraDTO;
import co.douglas.proyecto.dto.compra.ItemCompraDTO;

import java.util.List;

public interface CompraServicio {

    // Método para registrar una nueva compra
    DetalleCompraDTO guardarCompra(GuardarCompraDTO guardarCompraDTO) throws Exception;

    // Método para modificar una compra existente
    void modificarCompra(int idCompra, DetalleCompraDTO detalleCompraDTO) throws Exception;

    // Método para eliminar una compra
    void eliminarCompra(int idCompra) throws Exception;

    // Método para obtener los detalles de una compra específica
    DetalleCompraDTO detalleCompra(int idCompra) throws Exception;

    // Método para listar todas las compras
    List<ItemCompraDTO> listaCompras() throws Exception;
}
