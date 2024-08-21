package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer>{
}
