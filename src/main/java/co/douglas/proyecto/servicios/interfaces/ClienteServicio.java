package co.douglas.proyecto.servicios.interfaces;

import co.douglas.proyecto.dto.cliente.DetalleClienteDTO;
import co.douglas.proyecto.dto.cliente.GuardarClienteDTO;
import co.douglas.proyecto.dto.cliente.ItemClienteDTO;

import java.util.List;

public interface ClienteServicio {
    DetalleClienteDTO guardarCliente(GuardarClienteDTO clienteDTO) throws Exception;
    DetalleClienteDTO modificarCliente(int idCliente, GuardarClienteDTO clienteDTO) throws Exception;
    void eliminarCliente(int idCliente) throws Exception;
    DetalleClienteDTO detalleCliente(int idCliente) throws Exception;
    DetalleClienteDTO detalleClientePorCedula(String cedula) throws Exception;
    List<ItemClienteDTO> listarClientes();
    List<DetalleClienteDTO> listarClientesPorEstado(int estado);
    List<DetalleClienteDTO> buscarClientesPorNombre(String nombre);
}