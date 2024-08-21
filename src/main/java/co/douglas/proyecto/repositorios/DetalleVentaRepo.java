package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepo extends JpaRepository<DetalleVenta, Integer>{
}
