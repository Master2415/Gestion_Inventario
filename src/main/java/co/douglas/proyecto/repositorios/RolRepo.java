package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepo extends JpaRepository<Rol, Integer>{
}
