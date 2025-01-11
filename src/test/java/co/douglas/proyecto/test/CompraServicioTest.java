package co.douglas.proyecto.test;

import co.douglas.proyecto.dto.compra.DetalleCompraDTO;
import co.douglas.proyecto.dto.compra.GuardarCompraDTO;
import co.douglas.proyecto.dto.compra.ItemCompraDTO;
import co.douglas.proyecto.servicios.interfaces.CompraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class CompraServicioTest {

    @Autowired
    private CompraServicio compraServicio;

    @Test
    public void registrarCompraTest() throws Exception {

        // Creamos un objeto con los datos de la compra
        GuardarCompraDTO guardarCompraDTO = new GuardarCompraDTO(
                "12312",
                32.0,
                32000.0,
                LocalDate.of(2025, 1, 12),
                1,
                3,
                5,
                1);

        //guardamos la compra usando el servicio
        DetalleCompraDTO newBuy = compraServicio.guardarCompra(guardarCompraDTO);
        Assertions.assertNotNull(newBuy);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCompraTest() throws Exception {
        // Obtenemos la compra para la modificación
        DetalleCompraDTO compraGuardada = compraServicio.detalleCompra(29);

        // Validamos que la compra exista
        Assertions.assertNotNull(compraGuardada, "La compra no existe en la base de datos");

        // Validamos valores iniciales antes de modificar
        Assertions.assertNotEquals(42.1, compraGuardada.cantidadStock(), "El stock ya tiene el valor esperado");
        Assertions.assertNotEquals(28000.0, compraGuardada.precio(), "El precio ya tiene el valor esperado");

        // Creamos el DTO con los nuevos valores
        DetalleCompraDTO change = new DetalleCompraDTO(
                compraGuardada.idCompra(),
                42.1, // Nuevo stock
                28000.0, // Nuevo precio
                compraGuardada.fechaIngreso(), // Conservamos la fecha original
                compraGuardada.estado(), // Conservamos el estado original
                compraGuardada.idProducto(), // Conservamos el producto original
                compraGuardada.idProveedor(), // Conservamos el proveedor original
                compraGuardada.idUsuario() // Conservamos el usuario original
        );

        // Invocamos el método de modificación
        compraServicio.modificarCompra(29, change);

        // Obtenemos nuevamente la compra para verificar los cambios
        DetalleCompraDTO compraActualizada = compraServicio.detalleCompra(29);

        // Validamos que la compra se haya actualizado
        Assertions.assertNotNull(compraActualizada, "La compra no se actualizó correctamente");
        Assertions.assertEquals(42.1, compraActualizada.cantidadStock(), "El stock no se actualizó correctamente");
        Assertions.assertEquals(28000.0, compraActualizada.precio(), "El precio no se actualizó correctamente");

        // Validamos que los demás valores no hayan cambiado
        Assertions.assertEquals(compraGuardada.fechaIngreso(), compraActualizada.fechaIngreso(), "La fecha de ingreso no debería haber cambiado");
        Assertions.assertEquals(compraGuardada.estado(), compraActualizada.estado(), "El estado no debería haber cambiado");
        Assertions.assertEquals(compraGuardada.idProducto(), compraActualizada.idProducto(), "El ID del producto no debería haber cambiado");
        Assertions.assertEquals(compraGuardada.idProveedor(), compraActualizada.idProveedor(), "El ID del proveedor no debería haber cambiado");
        Assertions.assertEquals(compraGuardada.idUsuario(), compraActualizada.idUsuario(), "El ID del usuario no debería haber cambiado");
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTest() throws Exception {
        // Verifica que la compra exista antes de eliminarla
        DetalleCompraDTO compraGuardada = compraServicio.detalleCompra(29);
        Assertions.assertNotNull(compraGuardada, "La compra no debería ser nula antes de eliminarla");

        // Elimina la compra
        compraServicio.eliminarCompra(29);

        // Verifica que ya no existe en el repositorio
        Assertions.assertThrows(Exception.class, () -> compraServicio.detalleCompra(29));
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarTest() throws Exception {
        List<ItemCompraDTO> list = compraServicio.listaCompras();
        list.forEach(System.out::println);
        Assertions.assertEquals(2, list.size());
    }


}
