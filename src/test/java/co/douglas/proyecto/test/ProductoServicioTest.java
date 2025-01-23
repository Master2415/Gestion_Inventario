package co.douglas.proyecto.test;

import co.douglas.proyecto.dto.producto.DetalleProductoDTO;
import co.douglas.proyecto.dto.producto.GuardarProductoDTO;
import co.douglas.proyecto.dto.producto.ItemProductoDTO;
import co.douglas.proyecto.modelo.enumeraciones.TipoProducto;
import co.douglas.proyecto.servicios.interfaces.ProductoServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional // Asegura que los cambios realizados en cada prueba se reviertan automáticamente
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para manejar datos de prueba de forma más eficiente
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;

    @BeforeEach
    @Sql("classpath:dataset.sql") // Inicializa los datos automáticamente antes de cada prueba
    public void setUp() {
        // Aquí puedes realizar configuraciones adicionales si es necesario
    }

    @AfterEach
    public void tearDown() {
        // Limpia la base de datos solo si no se usan transacciones
    }

    @Test
    public void registrarProductoTest() throws Exception {
        // Creamos un DTO con los datos del producto
        GuardarProductoDTO nuevoProducto = new GuardarProductoDTO(
                "CAR001",
                "Costilla Premium",
                TipoProducto.RES,
                25.0,
                19.0,
                15000.0,
                50.0,
                12000.0,
                1
        );

        // Guardamos el producto
        DetalleProductoDTO productoGuardado = productoServicio.registrarProducto(nuevoProducto);

        // Verificamos que el producto haya sido guardado
        Assertions.assertNotNull(productoGuardado, "El producto no debería ser nulo tras guardarse.");
        Assertions.assertEquals("Costilla Premium", productoGuardado.nombre(), "El nombre del producto no coincide.");
    }

    @Test
    public void actualizarProductoTest() throws Exception {
        // Obtenemos un producto existente
        DetalleProductoDTO productoExistente = productoServicio.detalleProducto(1);

        // Validamos que el producto exista
        Assertions.assertNotNull(productoExistente, "El producto no existe en la base de datos.");

        // Creamos un DTO con los nuevos valores
        GuardarProductoDTO productoActualizado = new GuardarProductoDTO(
                productoExistente.codigo(),
                "Costilla de Res Actualizada",
                productoExistente.tipoProducto(),
                30.0,
                19.0,
                16000.0,
                40.0,
                13500.0,
                1
        );

        // Actualizamos el producto
        DetalleProductoDTO productoModificado = productoServicio.actualizarProducto(productoExistente.idProducto(), productoActualizado);

        // Verificamos que los cambios hayan sido aplicados
        Assertions.assertNotNull(productoModificado, "El producto actualizado no debería ser nulo.");
        Assertions.assertEquals("Costilla de Res Actualizada", productoModificado.nombre(), "El nombre del producto no se actualizó correctamente.");
        Assertions.assertEquals(16000.0, productoModificado.precioTotal(), "El precio total no se actualizó correctamente.");
    }

    @Test
    public void eliminarProductoTest() throws Exception {
        // Eliminamos un producto existente
        productoServicio.eliminarProducto(1);

        // Verificamos que el producto ya no exista
        Assertions.assertThrows(Exception.class, () -> productoServicio.detalleProducto(1), "El producto no debería existir tras ser eliminado.");
    }

    @Test
    public void listarProductosTest() throws Exception {
        // Obtenemos la lista de productos
        List<ItemProductoDTO> productos = productoServicio.listaProductos();

        // Verificamos que la lista no sea nula y tenga los elementos esperados
        Assertions.assertNotNull(productos, "La lista de productos no debería ser nula.");
        Assertions.assertFalse(productos.isEmpty(), "La lista de productos no debería estar vacía.");
        Assertions.assertEquals(1, productos.size(), "La cantidad de productos no coincide con lo esperado.");
    }

    @Test
    public void detalleProductoTest() throws Exception {
        // Obtenemos los detalles de un producto existente
        DetalleProductoDTO producto = productoServicio.detalleProducto(1);

        // Validamos que los detalles sean correctos
        Assertions.assertNotNull(producto, "El detalle del producto no debería ser nulo.");
        Assertions.assertEquals("CAR123-0", producto.codigo(), "El código del producto no coincide.");
        Assertions.assertEquals("Bife de Res", producto.nombre(), "El nombre del producto no coincide.");
    }
}
