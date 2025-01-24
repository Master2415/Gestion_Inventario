package co.douglas.proyecto.servicios.interfaces;

import co.douglas.proyecto.dto.empleado.DetalleEmpleadoDTO;
import co.douglas.proyecto.dto.empleado.GuardarEmpleadoDTO;
import co.douglas.proyecto.dto.empleado.ItemEmpleadoDTO;

import java.util.List;

public interface EmpleadoServicio {
    DetalleEmpleadoDTO guardarEmpleado(GuardarEmpleadoDTO empleadoDTO) throws Exception;
    DetalleEmpleadoDTO modificarEmpleado(int idEmpleado, GuardarEmpleadoDTO empleadoDTO) throws Exception;
    void eliminarEmpleado(int idEmpleado) throws Exception;
    DetalleEmpleadoDTO detalleEmpleado(int idEmpleado) throws Exception;
    DetalleEmpleadoDTO detalleEmpleadoPorCedula(String cedula) throws Exception;
    List<ItemEmpleadoDTO> listarEmpleados();
    List<DetalleEmpleadoDTO> listarEmpleadosPorEstado(int estado);
    List<DetalleEmpleadoDTO> buscarEmpleadosPorNombre(String nombre);
}