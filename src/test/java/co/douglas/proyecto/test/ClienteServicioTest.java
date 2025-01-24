package co.douglas.proyecto.test;

import co.douglas.proyecto.dto.cliente.DetalleClienteDTO;
import co.douglas.proyecto.dto.cliente.GuardarClienteDTO;
import co.douglas.proyecto.dto.cliente.ItemClienteDTO;
import co.douglas.proyecto.servicios.interfaces.ClienteServicio;
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
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @BeforeEach
    @Sql("classpath:dataset.sql")
    public void setUp() {
        // Configuración inicial para cada prueba
    }

    @Test
    public void guardarClienteTest() throws Exception {
        GuardarClienteDTO nuevoCliente = new GuardarClienteDTO(
                "1234567890",
                "Juan",
                "Pérez",
                "Calle Falsa 123",
                "3101234567",
                "juan@example.com",
                100,
                1
        );

        DetalleClienteDTO clienteGuardado = clienteServicio.guardarCliente(nuevoCliente);

        Assertions.assertNotNull(clienteGuardado, "El cliente no debería ser nulo tras guardarse.");
        Assertions.assertEquals("Juan", clienteGuardado.nombre(), "El nombre del cliente no coincide.");
        Assertions.assertEquals("1234567890", clienteGuardado.cedula(), "La cédula del cliente no coincide.");
    }

    @Test
    public void modificarClienteTest() throws Exception {
        // Primero creamos un cliente
        GuardarClienteDTO nuevoCliente = new GuardarClienteDTO(
                "9876543210",
                "María",
                "González",
                "Avenida Siempre Viva 742",
                "3209876543",
                "maria@example.com",
                50,
                1
        );
        DetalleClienteDTO clienteCreado = clienteServicio.guardarCliente(nuevoCliente);

        // Modificamos el cliente
        GuardarClienteDTO clienteActualizado = new GuardarClienteDTO(
                clienteCreado.cedula(),
                "María Modificada",
                "González Actualizada",
                "Nueva Dirección",
                "3211112222",
                "maria-modificada@example.com",
                75,
                1
        );

        DetalleClienteDTO clienteModificado = clienteServicio.modificarCliente(
                clienteCreado.idCliente(),
                clienteActualizado
        );

        Assertions.assertNotNull(clienteModificado, "El cliente modificado no debería ser nulo.");
        Assertions.assertEquals("María Modificada", clienteModificado.nombre(), "El nombre no se actualizó correctamente.");
        Assertions.assertEquals("Nueva Dirección", clienteModificado.direccion(), "La dirección no se actualizó correctamente.");
    }

    @Test
    public void eliminarClienteTest() throws Exception {
        // Primero creamos un cliente para eliminar
        GuardarClienteDTO nuevoCliente = new GuardarClienteDTO(
                "5555555555",
                "Cliente",
                "A Eliminar",
                "Dirección Test",
                "3333333333",
                "eliminar@example.com",
                0,
                1
        );
        DetalleClienteDTO clienteCreado = clienteServicio.guardarCliente(nuevoCliente);

        // Eliminamos el cliente
        clienteServicio.eliminarCliente(clienteCreado.idCliente());

        // Verificamos que el cliente ya no existe
        Assertions.assertThrows(Exception.class, () ->
                        clienteServicio.detalleCliente(clienteCreado.idCliente()),
                "El cliente no debería existir tras ser eliminado."
        );
    }

    @Test
    public void listarClientesTest() throws Exception {
        // Crear algunos clientes
        clienteServicio.guardarCliente(new GuardarClienteDTO(
                "1111111111",
                "Cliente",
                "Uno",
                "Dirección 1",
                "3111111111",
                "cliente1@example.com",
                10,
                1
        ));

        clienteServicio.guardarCliente(new GuardarClienteDTO(
                "2222222222",
                "Cliente",
                "Dos",
                "Dirección 2",
                "3222222222",
                "cliente2@example.com",
                20,
                1
        ));

        List<ItemClienteDTO> clientes = clienteServicio.listarClientes();

        Assertions.assertNotNull(clientes, "La lista de clientes no debería ser nula.");
        Assertions.assertFalse(clientes.isEmpty(), "La lista de clientes no debería estar vacía.");
    }

    @Test
    public void listarClientesPorEstadoTest() throws Exception {
        // Crear clientes con diferentes estados
        clienteServicio.guardarCliente(new GuardarClienteDTO(
                "3333333333",
                "Cliente",
                "Activo",
                "Dirección Activo",
                "3333333333",
                "activo@example.com",
                30,
                1
        ));

        clienteServicio.guardarCliente(new GuardarClienteDTO(
                "4444444444",
                "Cliente",
                "Inactivo",
                "Dirección Inactivo",
                "3444444444",
                "inactivo@example.com",
                40,
                0
        ));

        List<DetalleClienteDTO> clientesActivos = clienteServicio.listarClientesPorEstado(1);

        Assertions.assertNotNull(clientesActivos, "La lista de clientes activos no debería ser nula.");
        Assertions.assertFalse(clientesActivos.isEmpty(), "Debería haber clientes activos.");
        Assertions.assertTrue(clientesActivos.stream().allMatch(c -> c.estado() == 1),
                "Todos los clientes en la lista deberían estar activos.");
    }

    @Test
    public void buscarClientesPorNombreTest() throws Exception {
        // Crear clientes con nombres similares
        clienteServicio.guardarCliente(new GuardarClienteDTO(
                "6666666666",
                "Carlos",
                "Pérez",
                "Dirección Carlos",
                "3666666666",
                "carlos@example.com",
                60,
                1
        ));

        clienteServicio.guardarCliente(new GuardarClienteDTO(
                "7777777777",
                "Carla",
                "Gómez",
                "Dirección Carla",
                "3777777777",
                "carla@example.com",
                70,
                1
        ));

        List<DetalleClienteDTO> clientesEncontrados = clienteServicio.buscarClientesPorNombre("Car");

        Assertions.assertNotNull(clientesEncontrados, "La lista de clientes encontrados no debería ser nula.");
        Assertions.assertFalse(clientesEncontrados.isEmpty(), "Debería encontrar clientes con el nombre especificado.");
        Assertions.assertTrue(clientesEncontrados.stream()
                        .anyMatch(c -> c.nombre().toLowerCase().contains("car")),
                "Debería encontrar clientes con nombres que contengan 'car'.");
    }
}
