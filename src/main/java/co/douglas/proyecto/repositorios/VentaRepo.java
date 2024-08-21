package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepo  extends JpaRepository<Venta, Integer>{
}
