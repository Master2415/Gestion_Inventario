package co.douglas.proyecto.servicios.implementaciones;

import co.douglas.proyecto.dto.empleado.DetalleEmpleadoDTO;
import co.douglas.proyecto.dto.empleado.GuardarEmpleadoDTO;
import co.douglas.proyecto.dto.empleado.ItemEmpleadoDTO;
import co.douglas.proyecto.modelo.entidades.Empleado;
import co.douglas.proyecto.modelo.entidades.Rol;
import co.douglas.proyecto.repositorios.EmpleadoRepo;
import co.douglas.proyecto.repositorios.RolRepo;
import co.douglas.proyecto.servicios.interfaces.EmpleadoServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {

    private final EmpleadoRepo empleadoRepositorio;
    private final RolRepo rolRepositorio;

    public EmpleadoServicioImpl(EmpleadoRepo empleadoRepositorio, RolRepo rolRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
        this.rolRepositorio = rolRepositorio;
    }

    @Override
    public DetalleEmpleadoDTO guardarEmpleado(GuardarEmpleadoDTO empleadoDTO) throws Exception {
        Optional<Empleado> empleadoExistente = empleadoRepositorio.findByCedula(empleadoDTO.cedula());
        if (empleadoExistente.isPresent()) {
            throw new Exception("Ya existe un empleado con esta cédula.");
        }

        Optional<Rol> rol = rolRepositorio.findById(empleadoDTO.idRol());
        if (rol.isEmpty()) {
            throw new Exception("El rol especificado no existe.");
        }

        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setCedula(empleadoDTO.cedula());
        nuevoEmpleado.setNombre(empleadoDTO.nombre());
        nuevoEmpleado.setApellido(empleadoDTO.apellido());
        nuevoEmpleado.setDireccion(empleadoDTO.direccion());
        nuevoEmpleado.setTelefono(empleadoDTO.telefono());
        nuevoEmpleado.setEmail(empleadoDTO.email());
        nuevoEmpleado.setHorasTrabajadas(empleadoDTO.horasTrabajadas());
        nuevoEmpleado.setFechaContratacion(empleadoDTO.fechaContratacion());
        nuevoEmpleado.setEstado(empleadoDTO.estado());
        nuevoEmpleado.setRol(rol.get());

        Empleado empleadoGuardado = empleadoRepositorio.save(nuevoEmpleado);

        return new DetalleEmpleadoDTO(
                empleadoGuardado.getIdEmpleado(),
                empleadoGuardado.getCedula(),
                empleadoGuardado.getNombre(),
                empleadoGuardado.getApellido(),
                empleadoGuardado.getDireccion(),
                empleadoGuardado.getTelefono(),
                empleadoGuardado.getEmail(),
                empleadoGuardado.getHorasTrabajadas(),
                empleadoGuardado.getFechaContratacion(),
                empleadoGuardado.getEstado(),
                empleadoGuardado.getRol().getIdRol()
        );
    }

    @Override
    public DetalleEmpleadoDTO modificarEmpleado(int idEmpleado, GuardarEmpleadoDTO empleadoDTO) throws Exception {
        Optional<Empleado> empleadoExistente = empleadoRepositorio.findById(idEmpleado);
        if (empleadoExistente.isEmpty()) {
            throw new Exception("No existe un empleado con el ID " + idEmpleado);
        }

        Optional<Rol> rol = rolRepositorio.findById(empleadoDTO.idRol());
        if (rol.isEmpty()) {
            throw new Exception("El rol especificado no existe.");
        }

        Empleado empleado = empleadoExistente.get();
        empleado.setNombre(empleadoDTO.nombre());
        empleado.setApellido(empleadoDTO.apellido());
        empleado.setDireccion(empleadoDTO.direccion());
        empleado.setTelefono(empleadoDTO.telefono());
        empleado.setEmail(empleadoDTO.email());
        empleado.setHorasTrabajadas(empleadoDTO.horasTrabajadas());
        empleado.setEstado(empleadoDTO.estado());
        empleado.setRol(rol.get());

        Empleado empleadoActualizado = empleadoRepositorio.save(empleado);

        return new DetalleEmpleadoDTO(
                empleadoActualizado.getIdEmpleado(),
                empleadoActualizado.getCedula(),
                empleadoActualizado.getNombre(),
                empleadoActualizado.getApellido(),
                empleadoActualizado.getDireccion(),
                empleadoActualizado.getTelefono(),
                empleadoActualizado.getEmail(),
                empleadoActualizado.getHorasTrabajadas(),
                empleadoActualizado.getFechaContratacion(),
                empleadoActualizado.getEstado(),
                empleadoActualizado.getRol().getIdRol()
        );
    }

    @Override
    public void eliminarEmpleado(int idEmpleado) throws Exception {
        Optional<Empleado> empleado = empleadoRepositorio.findById(idEmpleado);
        if (empleado.isEmpty()) {
            throw new Exception("No existe un empleado con el ID " + idEmpleado);
        }
        empleadoRepositorio.delete(empleado.get());
    }

    @Override
    public DetalleEmpleadoDTO detalleEmpleado(int idEmpleado) throws Exception {
        Optional<Empleado> empleado = empleadoRepositorio.findById(idEmpleado);
        if (empleado.isEmpty()) {
            throw new Exception("No existe un empleado con el ID " + idEmpleado);
        }

        Empleado detalleEmpleado = empleado.get();
        return new DetalleEmpleadoDTO(
                detalleEmpleado.getIdEmpleado(),
                detalleEmpleado.getCedula(),
                detalleEmpleado.getNombre(),
                detalleEmpleado.getApellido(),
                detalleEmpleado.getDireccion(),
                detalleEmpleado.getTelefono(),
                detalleEmpleado.getEmail(),
                detalleEmpleado.getHorasTrabajadas(),
                detalleEmpleado.getFechaContratacion(),
                detalleEmpleado.getEstado(),
                detalleEmpleado.getRol().getIdRol()
        );
    }

    @Override
    public DetalleEmpleadoDTO detalleEmpleadoPorCedula(String cedula) throws Exception {
        Optional<Empleado> empleado = empleadoRepositorio.findByCedula(cedula);
        if (empleado.isEmpty()) {
            throw new Exception("No existe un empleado con la cédula " + cedula);
        }

        Empleado detalleEmpleado = empleado.get();
        return new DetalleEmpleadoDTO(
                detalleEmpleado.getIdEmpleado(),
                detalleEmpleado.getCedula(),
                detalleEmpleado.getNombre(),
                detalleEmpleado.getApellido(),
                detalleEmpleado.getDireccion(),
                detalleEmpleado.getTelefono(),
                detalleEmpleado.getEmail(),
                detalleEmpleado.getHorasTrabajadas(),
                detalleEmpleado.getFechaContratacion(),
                detalleEmpleado.getEstado(),
                detalleEmpleado.getRol().getIdRol()
        );
    }

    @Override
    public List<ItemEmpleadoDTO> listarEmpleados() {
        List<Empleado> empleados = empleadoRepositorio.findAll();
        List<ItemEmpleadoDTO> listaEmpleados = new ArrayList<>();

        for (Empleado empleado : empleados) {
            listaEmpleados.add(new ItemEmpleadoDTO(
                    empleado.getIdEmpleado(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getCedula()
            ));
        }

        return listaEmpleados;
    }

    @Override
    public List<DetalleEmpleadoDTO> listarEmpleadosPorEstado(int estado) {
        List<Empleado> empleados = empleadoRepositorio.findByEstado(estado);
        List<DetalleEmpleadoDTO> listaEmpleados = new ArrayList<>();

        for (Empleado empleado : empleados) {
            listaEmpleados.add(new DetalleEmpleadoDTO(
                    empleado.getIdEmpleado(),
                    empleado.getCedula(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getDireccion(),
                    empleado.getTelefono(),
                    empleado.getEmail(),
                    empleado.getHorasTrabajadas(),
                    empleado.getFechaContratacion(),
                    empleado.getEstado(),
                    empleado.getRol().getIdRol()
            ));
        }

        return listaEmpleados;
    }

    @Override
    public List<DetalleEmpleadoDTO> buscarEmpleadosPorNombre(String nombre) {
        List<Empleado> empleados = empleadoRepositorio.findByNombreContainingIgnoreCase(nombre);
        List<DetalleEmpleadoDTO> listaEmpleados = new ArrayList<>();

        for (Empleado empleado : empleados) {
            listaEmpleados.add(new DetalleEmpleadoDTO(
                    empleado.getIdEmpleado(),
                    empleado.getCedula(),
                    empleado.getNombre(),
                    empleado.getApellido(),
                    empleado.getDireccion(),
                    empleado.getTelefono(),
                    empleado.getEmail(),
                    empleado.getHorasTrabajadas(),
                    empleado.getFechaContratacion(),
                    empleado.getEstado(),
                    empleado.getRol().getIdRol()
            ));
        }

        return listaEmpleados;
    }
}