package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer>{
}
