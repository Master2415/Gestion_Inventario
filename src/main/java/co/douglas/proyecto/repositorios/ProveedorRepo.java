package co.douglas.proyecto.repositorios;

import co.douglas.proyecto.modelo.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepo extends JpaRepository<Proveedor, Integer> {
    List<Proveedor> findByEstado(int estado);
    List<Proveedor> findByTipoProveedor(String tipoProveedor);
}
