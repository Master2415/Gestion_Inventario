package co.douglas.proyecto.dto.empleado;

import java.util.Date;
import java.util.List;


public record ItemEmpleadoDTO(
        int idEmpleado,
        String nombre,
        String apellido,
        String cedula
) {}