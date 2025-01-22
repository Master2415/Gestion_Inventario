package co.douglas.proyecto.servicios.implementaciones;

import co.douglas.proyecto.dto.producto.DetalleProductoDTO;
import co.douglas.proyecto.dto.producto.GuardarProductoDTO;
import co.douglas.proyecto.dto.producto.ItemProductoDTO;
import co.douglas.proyecto.modelo.entidades.Producto;
import co.douglas.proyecto.repositorios.ProductoRepo;
import co.douglas.proyecto.servicios.interfaces.ProductoServicio;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepositorio;

    public ProductoServicioImpl(ProductoRepo productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public DetalleProductoDTO registrarProducto(GuardarProductoDTO productoDTO) throws Exception {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setCodigo(productoDTO.codigo());
        nuevoProducto.setEstado(productoDTO.estado());
        nuevoProducto.setIva(productoDTO.iva());
        nuevoProducto.setNombre(productoDTO.nombre());
        nuevoProducto.setPrecioNeto(productoDTO.precioNeto());
        nuevoProducto.setPrecioTotal(productoDTO.precioTotal());
        nuevoProducto.setStock(productoDTO.stock());
        nuevoProducto.setTipoProducto(productoDTO.tipoProducto());
        nuevoProducto.setUtilidad(productoDTO.utilidad());

        Producto productoGuardado = productoRepositorio.save(nuevoProducto);

        return new DetalleProductoDTO(
                productoGuardado.getIdProducto(),
                productoGuardado.getCodigo(),
                productoGuardado.getEstado(),
                productoGuardado.getIva(),
                productoGuardado.getNombre(),
                productoGuardado.getPrecioNeto(),
                productoGuardado.getPrecioTotal(),
                productoGuardado.getStock(),
                productoGuardado.getTipoProducto(),
                productoGuardado.getUtilidad()
        );
    }

    @Override
    public DetalleProductoDTO actualizarProducto(int idProducto, GuardarProductoDTO productoDTO) throws Exception {
        Optional<Producto> productoExistente = productoRepositorio.findById(idProducto);
        if (productoExistente.isEmpty()) {
            throw new Exception("No existe un producto con el ID " + idProducto);
        }

        Producto producto = productoExistente.get();
        producto.setCodigo(productoDTO.codigo());
        producto.setEstado(productoDTO.estado());
        producto.setIva(productoDTO.iva());
        producto.setNombre(productoDTO.nombre());
        producto.setPrecioNeto(productoDTO.precioNeto());
        producto.setPrecioTotal(productoDTO.precioTotal());
        producto.setStock(productoDTO.stock());
        producto.setTipoProducto(productoDTO.tipoProducto());
        producto.setUtilidad(productoDTO.utilidad());

        Producto productoActualizado = productoRepositorio.save(producto);

        return new DetalleProductoDTO(
                productoActualizado.getIdProducto(),
                productoActualizado.getCodigo(),
                productoActualizado.getEstado(),
                productoActualizado.getIva(),
                productoActualizado.getNombre(),
                productoActualizado.getPrecioNeto(),
                productoActualizado.getPrecioTotal(),
                productoActualizado.getStock(),
                productoActualizado.getTipoProducto(),
                productoActualizado.getUtilidad()
        );
    }

    @Override
    public void eliminarProducto(int idProducto) throws Exception {
        Optional<Producto> producto = productoRepositorio.findById(idProducto);
        if (producto.isEmpty()) {
            throw new Exception("No existe un producto con el ID " + idProducto);
        }
        productoRepositorio.delete(producto.get());
    }

    @Override
    public DetalleProductoDTO detalleProducto(int idProducto) throws Exception {
        Optional<Producto> producto = productoRepositorio.findById(idProducto);
        if (producto.isEmpty()) {
            throw new Exception("No existe un producto con el ID " + idProducto);
        }

        Producto detalleProducto = producto.get();
        return new DetalleProductoDTO(
                detalleProducto.getIdProducto(),
                detalleProducto.getCodigo(),
                detalleProducto.getEstado(),
                detalleProducto.getIva(),
                detalleProducto.getNombre(),
                detalleProducto.getPrecioNeto(),
                detalleProducto.getPrecioTotal(),
                detalleProducto.getStock(),
                detalleProducto.getTipoProducto(),
                detalleProducto.getUtilidad()
        );
    }

    @Override
    public List<ItemProductoDTO> listaProductos() throws Exception {
        List<Producto> productos = productoRepositorio.findAll();
        List<ItemProductoDTO> listaProductos = new ArrayList<>();

        for (Producto producto : productos) {
            listaProductos.add(new ItemProductoDTO(
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getStock(),
                    producto.getPrecioTotal()
            ));
        }

        return listaProductos;
    }
}
