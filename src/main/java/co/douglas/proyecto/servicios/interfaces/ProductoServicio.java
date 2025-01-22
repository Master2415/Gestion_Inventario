package co.douglas.proyecto.servicios.interfaces;

import co.douglas.proyecto.dto.producto.DetalleProductoDTO;
import co.douglas.proyecto.dto.producto.GuardarProductoDTO;
import co.douglas.proyecto.dto.producto.ItemProductoDTO;

import java.util.List;

public interface ProductoServicio {


    DetalleProductoDTO registrarProducto(GuardarProductoDTO productoDTO) throws Exception;
    DetalleProductoDTO actualizarProducto(int idProducto, GuardarProductoDTO productoDTO) throws Exception;
    void eliminarProducto(int idProducto) throws Exception;
    DetalleProductoDTO detalleProducto(int idProducto) throws Exception;
    List<ItemProductoDTO> listaProductos() throws Exception;


}
