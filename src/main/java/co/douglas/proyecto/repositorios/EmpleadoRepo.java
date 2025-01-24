package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByCedula(String cedula);
    List<Empleado> findByEstado(int estado);
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);
}

