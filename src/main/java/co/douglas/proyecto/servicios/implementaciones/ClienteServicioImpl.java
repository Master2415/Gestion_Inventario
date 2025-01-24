package co.douglas.proyecto.servicios.implementaciones;

import co.douglas.proyecto.dto.cliente.DetalleClienteDTO;
import co.douglas.proyecto.dto.cliente.GuardarClienteDTO;
import co.douglas.proyecto.dto.cliente.ItemClienteDTO;
import co.douglas.proyecto.modelo.entidades.Cliente;
import co.douglas.proyecto.repositorios.ClienteRepo;
import co.douglas.proyecto.servicios.interfaces.ClienteServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepositorio;

    public ClienteServicioImpl(ClienteRepo clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public DetalleClienteDTO guardarCliente(GuardarClienteDTO clienteDTO) throws Exception {
        Optional<Cliente> clienteExistente = clienteRepositorio.findByCedula(clienteDTO.cedula());
        if (clienteExistente.isPresent()) {
            throw new Exception("Ya existe un cliente con esta cédula.");
        }

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setCedula(clienteDTO.cedula());
        nuevoCliente.setNombre(clienteDTO.nombre());
        nuevoCliente.setApellido(clienteDTO.apellido());
        nuevoCliente.setDireccion(clienteDTO.direccion());
        nuevoCliente.setTelefono(clienteDTO.telefono());
        nuevoCliente.setEmail(clienteDTO.email());
        nuevoCliente.setPuntos(clienteDTO.puntos());
        nuevoCliente.setEstado(clienteDTO.estado());
        nuevoCliente.setFechaRegistro(new Date());

        Cliente clienteGuardado = clienteRepositorio.save(nuevoCliente);

        return new DetalleClienteDTO(
                clienteGuardado.getIdCliente(),
                clienteGuardado.getCedula(),
                clienteGuardado.getNombre(),
                clienteGuardado.getApellido(),
                clienteGuardado.getDireccion(),
                clienteGuardado.getTelefono(),
                clienteGuardado.getEmail(),
                clienteGuardado.getPuntos(),
                clienteGuardado.getEstado()
        );
    }

    @Override
    public DetalleClienteDTO modificarCliente(int idCliente, GuardarClienteDTO clienteDTO) throws Exception {
        Optional<Cliente> clienteExistente = clienteRepositorio.findById(idCliente);

        if (clienteExistente.isEmpty()) {
            throw new Exception("No existe un cliente con el ID " + idCliente);
        }

        Cliente cliente = clienteExistente.get();
        cliente.setNombre(clienteDTO.nombre());
        cliente.setApellido(clienteDTO.apellido());
        cliente.setDireccion(clienteDTO.direccion());
        cliente.setTelefono(clienteDTO.telefono());
        cliente.setEmail(clienteDTO.email());
        cliente.setPuntos(clienteDTO.puntos());
        cliente.setEstado(clienteDTO.estado());

        Cliente clienteActualizado = clienteRepositorio.save(cliente);

        return new DetalleClienteDTO(
                clienteActualizado.getIdCliente(),
                clienteActualizado.getCedula(),
                clienteActualizado.getNombre(),
                clienteActualizado.getApellido(),
                clienteActualizado.getDireccion(),
                clienteActualizado.getTelefono(),
                clienteActualizado.getEmail(),
                clienteActualizado.getPuntos(),
                clienteActualizado.getEstado()
        );
    }

    @Override
    public void eliminarCliente(int idCliente) throws Exception {
        Optional<Cliente> cliente = clienteRepositorio.findById(idCliente);

        if (cliente.isEmpty()) {
            throw new Exception("No existe un cliente con el ID " + idCliente);
        }

        clienteRepositorio.delete(cliente.get());
    }

    @Override
    public DetalleClienteDTO detalleCliente(int idCliente) throws Exception {
        Optional<Cliente> cliente = clienteRepositorio.findById(idCliente);

        if (cliente.isEmpty()) {
            throw new Exception("No existe un cliente con el ID " + idCliente);
        }

        Cliente detalleCliente = cliente.get();

        return new DetalleClienteDTO(
                detalleCliente.getIdCliente(),
                detalleCliente.getCedula(),
                detalleCliente.getNombre(),
                detalleCliente.getApellido(),
                detalleCliente.getDireccion(),
                detalleCliente.getTelefono(),
                detalleCliente.getEmail(),
                detalleCliente.getPuntos(),
                detalleCliente.getEstado()
        );
    }

    @Override
    public DetalleClienteDTO detalleClientePorCedula(String cedula) throws Exception {
        Optional<Cliente> cliente = clienteRepositorio.findByCedula(cedula);

        if (cliente.isEmpty()) {
            throw new Exception("No existe un cliente con la cédula " + cedula);
        }

        Cliente detalleCliente = cliente.get();

        return new DetalleClienteDTO(
                detalleCliente.getIdCliente(),
                detalleCliente.getCedula(),
                detalleCliente.getNombre(),
                detalleCliente.getApellido(),
                detalleCliente.getDireccion(),
                detalleCliente.getTelefono(),
                detalleCliente.getEmail(),
                detalleCliente.getPuntos(),
                detalleCliente.getEstado()
        );
    }

    @Override
    public List<ItemClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        List<ItemClienteDTO> listaClientes = new ArrayList<>();

        for (Cliente cliente : clientes) {
            listaClientes.add(new ItemClienteDTO(
                    cliente.getIdCliente(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getCedula()
            ));
        }

        return listaClientes;
    }

    @Override
    public List<DetalleClienteDTO> listarClientesPorEstado(int estado) {
        List<Cliente> clientes = clienteRepositorio.findByEstado(estado);
        List<DetalleClienteDTO> listaClientes = new ArrayList<>();

        for (Cliente cliente : clientes) {
            listaClientes.add(new DetalleClienteDTO(
                    cliente.getIdCliente(),
                    cliente.getCedula(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getDireccion(),
                    cliente.getTelefono(),
                    cliente.getEmail(),
                    cliente.getPuntos(),
                    cliente.getEstado()
            ));
        }

        return listaClientes;
    }

    @Override
    public List<DetalleClienteDTO> buscarClientesPorNombre(String nombre) {
        List<Cliente> clientes = clienteRepositorio.findByNombreContainingIgnoreCase(nombre);
        List<DetalleClienteDTO> listaClientes = new ArrayList<>();

        for (Cliente cliente : clientes) {
            listaClientes.add(new DetalleClienteDTO(
                    cliente.getIdCliente(),
                    cliente.getCedula(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getDireccion(),
                    cliente.getTelefono(),
                    cliente.getEmail(),
                    cliente.getPuntos(),
                    cliente.getEstado()
            ));
        }

        return listaClientes;
    }
}