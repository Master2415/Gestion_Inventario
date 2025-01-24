package co.douglas.proyecto.test;

import co.douglas.proyecto.dto.proveedor.DetalleProveedorDTO;
import co.douglas.proyecto.dto.proveedor.GuardarProveedorDTO;
import co.douglas.proyecto.servicios.interfaces.ProveedorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ProveedorServicioTest {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @BeforeEach
    @Sql("classpath:dataset.sql")
    public void setUp() {
        // Configuración inicial para cada prueba
    }

    @Test
    public void guardarProveedorTest() throws Exception {
        // Crear un nuevo proveedor
        GuardarProveedorDTO nuevoProveedor = new GuardarProveedorDTO(
                "proveedor@example.com",
                "Calle Falsa 123",
                1,
                "Proveedor Test",
                "Carnes"
        );

        // Guardar el proveedor
        DetalleProveedorDTO proveedorGuardado = proveedorServicio.guardarProveedor(nuevoProveedor);

        // Verificaciones
        Assertions.assertNotNull(proveedorGuardado, "El proveedor no debería ser nulo tras guardarse.");
        Assertions.assertEquals("Proveedor Test", proveedorGuardado.nombre(), "El nombre del proveedor no coincide.");
        Assertions.assertEquals("proveedor@example.com", proveedorGuardado.correo(), "El correo del proveedor no coincide.");
    }

    @Test
    public void modificarProveedorTest() throws Exception {
        // Crear un proveedor inicial
        GuardarProveedorDTO nuevoProveedor = new GuardarProveedorDTO(
                "original@example.com",
                "Dirección Original",
                1,
                "Proveedor Original",
                "Carnes"
        );
        DetalleProveedorDTO proveedorCreado = proveedorServicio.guardarProveedor(nuevoProveedor);

        // Preparar datos para modificación
        GuardarProveedorDTO proveedorActualizado = new GuardarProveedorDTO(
                "actualizado@example.com",
                "Nueva Dirección",
                1,
                "Proveedor Actualizado",
                "Lácteos"
        );

        // Modificar el proveedor
        DetalleProveedorDTO proveedorModificado = proveedorServicio.modificarProveedor(
                proveedorCreado.idProveedor(),
                proveedorActualizado
        );

        // Verificaciones
        Assertions.assertNotNull(proveedorModificado, "El proveedor modificado no debería ser nulo.");
        Assertions.assertEquals("Proveedor Actualizado", proveedorModificado.nombre(), "El nombre no se actualizó correctamente.");
        Assertions.assertEquals("actualizado@example.com", proveedorModificado.correo(), "El correo no se actualizó correctamente.");
    }

    @Test
    public void eliminarProveedorTest() throws Exception {
        // Crear un proveedor para eliminar
        GuardarProveedorDTO nuevoProveedor = new GuardarProveedorDTO(
                "eliminar@example.com",
                "Dirección a Eliminar",
                1,
                "Proveedor a Eliminar",
                "Carnes"
        );
        DetalleProveedorDTO proveedorCreado = proveedorServicio.guardarProveedor(nuevoProveedor);

        // Eliminar el proveedor
        proveedorServicio.eliminarProveedor(proveedorCreado.idProveedor());

        // Verificar que el proveedor ya no existe
        Assertions.assertThrows(Exception.class, () ->
                        proveedorServicio.detalleProveedor(proveedorCreado.idProveedor()),
                "El proveedor no debería existir tras ser eliminado."
        );
    }

    @Test
    public void listarProveedoresTest() throws Exception {
        // Crear algunos proveedores para la lista
        proveedorServicio.guardarProveedor(new GuardarProveedorDTO(
                "proveedor1@example.com",
                "Dirección 1",
                1,
                "Proveedor 1",
                "Carnes"
        ));

        proveedorServicio.guardarProveedor(new GuardarProveedorDTO(
                "proveedor2@example.com",
                "Dirección 2",
                1,
                "Proveedor 2",
                "Lácteos"
        ));

        // Obtener la lista de proveedores
        List<DetalleProveedorDTO> proveedores = proveedorServicio.listarProveedores();

        // Verificaciones
        Assertions.assertNotNull(proveedores, "La lista de proveedores no debería ser nula.");
        Assertions.assertFalse(proveedores.isEmpty(), "La lista de proveedores no debería estar vacía.");
        Assertions.assertTrue(proveedores.size() >= 2, "Debería haber al menos 2 proveedores en la lista.");
    }

    @Test
    public void listarProveedoresPorEstadoTest() throws Exception {
        // Crear proveedores con diferentes estados
        proveedorServicio.guardarProveedor(new GuardarProveedorDTO(
                "activo@example.com",
                "Dirección Activo",
                1,
                "Proveedor Activo",
                "Carnes"
        ));

        proveedorServicio.guardarProveedor(new GuardarProveedorDTO(
                "inactivo@example.com",
                "Dirección Inactivo",
                0,
                "Proveedor Inactivo",
                "Lácteos"
        ));

        // Obtener proveedores activos
        List<DetalleProveedorDTO> proveedoresActivos = proveedorServicio.listarProveedoresPorEstado(1);

        // Verificaciones
        Assertions.assertNotNull(proveedoresActivos, "La lista de proveedores activos no debería ser nula.");
        Assertions.assertFalse(proveedoresActivos.isEmpty(), "Debería haber proveedores activos.");
        Assertions.assertTrue(proveedoresActivos.stream().allMatch(p -> p.estado() == 1),
                "Todos los proveedores en la lista deberían estar activos.");
    }

    @Test
    public void listarProveedoresPorTipoTest() throws Exception {
        // Crear proveedores de diferentes tipos
        proveedorServicio.guardarProveedor(new GuardarProveedorDTO(
                "carnes@example.com",
                "Dirección Carnes",
                1,
                "Proveedor Carnes",
                "Carnes"
        ));

        proveedorServicio.guardarProveedor(new GuardarProveedorDTO(
                "lacteos@example.com",
                "Dirección Lácteos",
                1,
                "Proveedor Lácteos",
                "Lácteos"
        ));

        // Obtener proveedores de un tipo específico
        List<DetalleProveedorDTO> proveedoresCarnes = proveedorServicio.listarProveedoresPorTipo("Carnes");

        // Verificaciones
        Assertions.assertNotNull(proveedoresCarnes, "La lista de proveedores de carnes no debería ser nula.");
        Assertions.assertFalse(proveedoresCarnes.isEmpty(), "Debería haber proveedores de carnes.");
        Assertions.assertTrue(proveedoresCarnes.stream().allMatch(p -> p.tipoProveedor().equals("Carnes")),
                "Todos los proveedores en la lista deberían ser de tipo Carnes.");
    }
}