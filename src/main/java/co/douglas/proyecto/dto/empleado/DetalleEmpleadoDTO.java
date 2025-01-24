package co.douglas.proyecto.dto.empleado;

import java.util.Date;
import java.util.List;

public record DetalleEmpleadoDTO(
        int idEmpleado,
        String cedula,
        String nombre,
        String apellido,
        String direccion,
        List<String> telefono,
        String email,
        Integer horasTrabajadas,
        Date fechaContratacion,
        int estado,
        int idRol
) {}
