package co.douglas.proyecto.servicios.implementaciones;

import co.douglas.proyecto.dto.compra.DetalleCompraDTO;
import co.douglas.proyecto.dto.compra.GuardarCompraDTO;
import co.douglas.proyecto.dto.compra.ItemCompraDTO;
import co.douglas.proyecto.modelo.entidades.Compra;
import co.douglas.proyecto.modelo.entidades.Producto;
import co.douglas.proyecto.modelo.entidades.Proveedor;
import co.douglas.proyecto.modelo.entidades.Usuario;
import co.douglas.proyecto.repositorios.CompraRepo;
import co.douglas.proyecto.repositorios.ProductoRepo;
import co.douglas.proyecto.repositorios.ProveedorRepo;
import co.douglas.proyecto.repositorios.UsuarioRepo;
import co.douglas.proyecto.servicios.interfaces.CompraServicio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepositorio;
    private final ProductoRepo productoRepositorio;
    private final ProveedorRepo proveedorRepositorio;
    private final UsuarioRepo usuarioRepositorio;

    public CompraServicioImpl(CompraRepo compraRepositorio,
                              ProductoRepo productoRepositorio,
                              ProveedorRepo proveedorRepositorio,
                              UsuarioRepo usuarioRepositorio) {
        this.compraRepositorio = compraRepositorio;
        this.productoRepositorio = productoRepositorio;
        this.proveedorRepositorio = proveedorRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public DetalleCompraDTO guardarCompra(GuardarCompraDTO compraDTO) throws Exception {
        Compra nuevaCompra = new Compra();

        // Asignar los valores desde el DTO a la entidad
        nuevaCompra.setCantidadStock(compraDTO.cantidadStock());
        nuevaCompra.setPrecio(compraDTO.precio());
        nuevaCompra.setFecha_Ingreso(compraDTO.fechaIngreso());
        nuevaCompra.setEstado(compraDTO.estado());

        // Buscar y asignar entidades relacionadas
        Producto producto = productoRepositorio.findById(compraDTO.idProducto())
                .orElseThrow(() -> new Exception("Producto no encontrado con ID: " + compraDTO.idProducto()));
        nuevaCompra.setProducto(producto);

        Proveedor proveedor = proveedorRepositorio.findById(compraDTO.idProveedor())
                .orElseThrow(() -> new Exception("Proveedor no encontrado con ID: " + compraDTO.idProveedor()));
        nuevaCompra.setProveedor(proveedor);

        Usuario usuario = usuarioRepositorio.findById(compraDTO.idUsuario())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + compraDTO.idUsuario()));
        nuevaCompra.setUsuario(usuario);

        Compra compraGuardada = compraRepositorio.save(nuevaCompra);

        return new DetalleCompraDTO(
                compraGuardada.getIdCompra(),
                compraGuardada.getCantidadStock(),
                compraGuardada.getPrecio(),
                compraGuardada.getFecha_Ingreso(),
                compraGuardada.getEstado(),
                compraGuardada.getProducto().getIdProducto(),
                compraGuardada.getProveedor().getIdProveedor(),
                compraGuardada.getUsuario().getIdUsuario()
        );
    }

    @Override
    public DetalleCompraDTO modificarCompra(int idCompra, GuardarCompraDTO compraDTO) throws Exception {
        Optional<Compra> compraExistente = compraRepositorio.findById(idCompra);
        if (compraExistente.isEmpty()) {
            throw new Exception("No existe una compra con el ID " + idCompra);
        }

        Compra compra = compraExistente.get();

        // Actualizar los valores desde el DTO
        compra.setCantidadStock(compraDTO.cantidadStock());
        compra.setPrecio(compraDTO.precio());
        compra.setFecha_Ingreso(compraDTO.fechaIngreso());
        compra.setEstado(compraDTO.estado());

        // Buscar y asignar entidades relacionadas
        Producto producto = productoRepositorio.findById(compraDTO.idProducto())
                .orElseThrow(() -> new Exception("Producto no encontrado con ID: " + compraDTO.idProducto()));
        compra.setProducto(producto);

        Proveedor proveedor = proveedorRepositorio.findById(compraDTO.idProveedor())
                .orElseThrow(() -> new Exception("Proveedor no encontrado con ID: " + compraDTO.idProveedor()));
        compra.setProveedor(proveedor);

        Usuario usuario = usuarioRepositorio.findById(compraDTO.idUsuario())
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + compraDTO.idUsuario()));
        compra.setUsuario(usuario);

        Compra compraActualizada = compraRepositorio.save(compra);

        return new DetalleCompraDTO(
                compraActualizada.getIdCompra(),
                compraActualizada.getCantidadStock(),
                compraActualizada.getPrecio(),
                compraActualizada.getFecha_Ingreso(),
                compraActualizada.getEstado(),
                compraActualizada.getProducto().getIdProducto(),
                compraActualizada.getProveedor().getIdProveedor(),
                compraActualizada.getUsuario().getIdUsuario()
        );
    }

    @Override
    public void eliminarCompra(int idCompra) throws Exception {
        Optional<Compra> compra = compraRepositorio.findById(idCompra);
        if (compra.isEmpty()) {
            throw new Exception("No existe una compra con el ID " + idCompra);
        }
        compraRepositorio.delete(compra.get());
    }

    @Override
    public DetalleCompraDTO detalleCompra(int idCompra) throws Exception {
        Optional<Compra> compra = compraRepositorio.findById(idCompra);
        if (compra.isEmpty()) {
            throw new Exception("No existe una compra con el ID " + idCompra);
        }

        Compra detalleCompra = compra.get();

        return new DetalleCompraDTO(
                detalleCompra.getIdCompra(),
                detalleCompra.getCantidadStock(),
                detalleCompra.getPrecio(),
                detalleCompra.getFecha_Ingreso(),
                detalleCompra.getEstado(),
                detalleCompra.getProducto().getIdProducto(),
                detalleCompra.getProveedor().getIdProveedor(),
                detalleCompra.getUsuario().getIdUsuario()
        );
    }

    @Override
    public List<ItemCompraDTO> listaCompras() throws Exception {
        List<Compra> compras = compraRepositorio.findAll();
        List<ItemCompraDTO> listaCompras = new ArrayList<>();

        for (Compra compra : compras) {
            listaCompras.add(new ItemCompraDTO(
                    compra.getCantidadStock(),
                    compra.getPrecio(),
                    compra.getEstado()
            ));
        }

        return listaCompras;
    }
}