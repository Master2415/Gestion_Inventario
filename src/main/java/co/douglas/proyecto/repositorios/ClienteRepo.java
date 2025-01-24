package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCedula(String cedula);
    List<Cliente> findByEstado(int estado);
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
}