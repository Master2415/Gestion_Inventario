package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{
    // Consulta personalizada para buscar un usuario por correo
    Optional<Usuario> findByCorreo(String correo);
}
