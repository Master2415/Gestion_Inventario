package co.douglas.proyecto.servicios.implementaciones;

import co.douglas.proyecto.dto.proveedor.DetalleProveedorDTO;
import co.douglas.proyecto.dto.proveedor.GuardarProveedorDTO;
import co.douglas.proyecto.modelo.entidades.Proveedor;
import co.douglas.proyecto.repositorios.ProveedorRepo;
import co.douglas.proyecto.servicios.interfaces.ProveedorServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServicioImpl implements ProveedorServicio {

    private final ProveedorRepo proveedorRepositorio;

    public ProveedorServicioImpl(ProveedorRepo proveedorRepositorio) {
        this.proveedorRepositorio = proveedorRepositorio;
    }

    @Override
    public DetalleProveedorDTO guardarProveedor(GuardarProveedorDTO proveedorDTO) throws Exception {
        Proveedor nuevoProveedor = new Proveedor();

        // Asignar los valores del DTO a la entidad
        nuevoProveedor.setCorreo(proveedorDTO.correo());
        nuevoProveedor.setDireccion(proveedorDTO.direccion());
        nuevoProveedor.setEstado(proveedorDTO.estado());
        nuevoProveedor.setNombre(proveedorDTO.nombre());
        nuevoProveedor.setTipoProveedor(proveedorDTO.tipoProveedor());

        Proveedor proveedorGuardado = proveedorRepositorio.save(nuevoProveedor);

        return new DetalleProveedorDTO(
                proveedorGuardado.getIdProveedor(),
                proveedorGuardado.getCorreo(),
                proveedorGuardado.getDireccion(),
                proveedorGuardado.getEstado(),
                proveedorGuardado.getNombre(),
                proveedorGuardado.getTipoProveedor()
        );
    }

    @Override
    public DetalleProveedorDTO modificarProveedor(int idProveedor, GuardarProveedorDTO proveedorDTO) throws Exception {
        Optional<Proveedor> proveedorExistente = proveedorRepositorio.findById(idProveedor);

        if (proveedorExistente.isEmpty()) {
            throw new Exception("No existe un proveedor con el ID " + idProveedor);
        }

        Proveedor proveedor = proveedorExistente.get();

        // Actualizar los valores desde el DTO
        proveedor.setCorreo(proveedorDTO.correo());
        proveedor.setDireccion(proveedorDTO.direccion());
        proveedor.setEstado(proveedorDTO.estado());
        proveedor.setNombre(proveedorDTO.nombre());
        proveedor.setTipoProveedor(proveedorDTO.tipoProveedor());

        Proveedor proveedorActualizado = proveedorRepositorio.save(proveedor);

        return new DetalleProveedorDTO(
                proveedorActualizado.getIdProveedor(),
                proveedorActualizado.getCorreo(),
                proveedorActualizado.getDireccion(),
                proveedorActualizado.getEstado(),
                proveedorActualizado.getNombre(),
                proveedorActualizado.getTipoProveedor()
        );
    }

    @Override
    public void eliminarProveedor(int idProveedor) throws Exception {
        Optional<Proveedor> proveedor = proveedorRepositorio.findById(idProveedor);

        if (proveedor.isEmpty()) {
            throw new Exception("No existe un proveedor con el ID " + idProveedor);
        }

        proveedorRepositorio.delete(proveedor.get());
    }

    @Override
    public DetalleProveedorDTO detalleProveedor(int idProveedor) throws Exception {
        Optional<Proveedor> proveedor = proveedorRepositorio.findById(idProveedor);

        if (proveedor.isEmpty()) {
            throw new Exception("No existe un proveedor con el ID " + idProveedor);
        }

        Proveedor detalleProveedor = proveedor.get();

        return new DetalleProveedorDTO(
                detalleProveedor.getIdProveedor(),
                detalleProveedor.getCorreo(),
                detalleProveedor.getDireccion(),
                detalleProveedor.getEstado(),
                detalleProveedor.getNombre(),
                detalleProveedor.getTipoProveedor()
        );
    }

    @Override
    public List<DetalleProveedorDTO> listarProveedores() {
        List<Proveedor> proveedores = proveedorRepositorio.findAll();
        List<DetalleProveedorDTO> listaProveedores = new ArrayList<>();

        for (Proveedor proveedor : proveedores) {
            listaProveedores.add(new DetalleProveedorDTO(
                    proveedor.getIdProveedor(),
                    proveedor.getCorreo(),
                    proveedor.getDireccion(),
                    proveedor.getEstado(),
                    proveedor.getNombre(),
                    proveedor.getTipoProveedor()
            ));
        }

        return listaProveedores;
    }

    @Override
    public List<DetalleProveedorDTO> listarProveedoresPorEstado(int estado) {
        List<Proveedor> proveedores = proveedorRepositorio.findByEstado(estado);
        List<DetalleProveedorDTO> listaProveedores = new ArrayList<>();

        for (Proveedor proveedor : proveedores) {
            listaProveedores.add(new DetalleProveedorDTO(
                    proveedor.getIdProveedor(),
                    proveedor.getCorreo(),
                    proveedor.getDireccion(),
                    proveedor.getEstado(),
                    proveedor.getNombre(),
                    proveedor.getTipoProveedor()
            ));
        }

        return listaProveedores;
    }

    @Override
    public List<DetalleProveedorDTO> listarProveedoresPorTipo(String tipo) {
        List<Proveedor> proveedores = proveedorRepositorio.findByTipoProveedor(tipo);
        List<DetalleProveedorDTO> listaProveedores = new ArrayList<>();

        for (Proveedor proveedor : proveedores) {
            listaProveedores.add(new DetalleProveedorDTO(
                    proveedor.getIdProveedor(),
                    proveedor.getCorreo(),
                    proveedor.getDireccion(),
                    proveedor.getEstado(),
                    proveedor.getNombre(),
                    proveedor.getTipoProveedor()
            ));
        }

        return listaProveedores;
    }
}
