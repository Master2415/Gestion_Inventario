package co.douglas.proyecto.test;

import co.douglas.proyecto.dto.empleado.DetalleEmpleadoDTO;
import co.douglas.proyecto.dto.empleado.GuardarEmpleadoDTO;
import co.douglas.proyecto.dto.empleado.ItemEmpleadoDTO;
import co.douglas.proyecto.servicios.interfaces.EmpleadoServicio;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional // Asegura que los cambios realizados en cada prueba se reviertan automáticamente
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para manejar datos de prueba de forma más eficiente
public class EmpleadoServicioTest {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @BeforeEach
    @Sql("classpath:dataset.sql") // Inicializa los datos automáticamente antes de cada prueba
    public void setUp() {
        // Configuración adicional si es necesaria
    }

    @Test
    public void registrarEmpleadoTest() throws Exception {
        // Creamos un DTO con los datos del empleado
        GuardarEmpleadoDTO nuevoEmpleado = new GuardarEmpleadoDTO(
                "1067891234", // Cédula
                "Carlos", // Nombre
                "Ramirez", // Apellido
                "Calle 90 #12-34", // Dirección
                List.of("3001234567"), // Teléfonos
                "carlos.ramirez@empresa.com", // Email
                180, // Horas trabajadas
                new Date(), // Fecha de contratación
                1, // Estado
                2 // ID Rol
        );

        // Guardamos el empleado
        DetalleEmpleadoDTO empleadoGuardado = empleadoServicio.guardarEmpleado(nuevoEmpleado);

        // Verificamos que el empleado haya sido guardado
        Assertions.assertNotNull(empleadoGuardado, "El empleado no debería ser nulo tras guardarse.");
        Assertions.assertEquals("Carlos", empleadoGuardado.nombre(), "El nombre del empleado no coincide.");
    }

    @Test
    public void actualizarEmpleadoTest() throws Exception {
        // Obtenemos un empleado existente
        DetalleEmpleadoDTO empleadoExistente = empleadoServicio.detalleEmpleado(1);

        // Validamos que el empleado exista
        Assertions.assertNotNull(empleadoExistente, "El empleado no existe en la base de datos.");

        // Creamos un DTO con los nuevos valores
        GuardarEmpleadoDTO empleadoActualizado = new GuardarEmpleadoDTO(
                empleadoExistente.cedula(),
                "Carlos Eduardo",
                "Ramirez Lopez",
                "Calle 80 #10-20",
                List.of("3109876543"),
                "carlos.e.ramirez@empresa.com",
                200,
                empleadoExistente.fechaContratacion(),
                empleadoExistente.estado(),
                empleadoExistente.idRol() // Nuevo rol
        );

        // Actualizamos el empleado
        DetalleEmpleadoDTO empleadoModificado = empleadoServicio.modificarEmpleado(empleadoExistente.idEmpleado(), empleadoActualizado);

        // Verificamos que los cambios hayan sido aplicados
        Assertions.assertNotNull(empleadoModificado, "El empleado actualizado no debería ser nulo.");
        Assertions.assertEquals("Carlos Eduardo", empleadoModificado.nombre(), "El nombre del empleado no se actualizó correctamente.");
        Assertions.assertEquals("Ramirez Lopez", empleadoModificado.apellido(), "El apellido no se actualizó correctamente.");
    }

    @Test
    public void eliminarEmpleadoTest() throws Exception {
        // Eliminamos un empleado existente
        empleadoServicio.eliminarEmpleado(1);

        // Verificamos que el empleado ya no exista
        Assertions.assertThrows(Exception.class, () -> empleadoServicio.detalleEmpleado(1), "El empleado no debería existir tras ser eliminado.");
    }

    @Test
    public void listarEmpleadosTest() {
        // Obtenemos la lista de empleados
        List<ItemEmpleadoDTO> empleados = empleadoServicio.listarEmpleados();

        // Verificamos que la lista no sea nula y tenga los elementos esperados
        Assertions.assertNotNull(empleados, "La lista de empleados no debería ser nula.");
        Assertions.assertFalse(empleados.isEmpty(), "La lista de empleados no debería estar vacía.");
    }

    @Test
    public void detalleEmpleadoTest() throws Exception {
        // Obtenemos los detalles de un empleado existente
        DetalleEmpleadoDTO empleado = empleadoServicio.detalleEmpleado(1);

        // Validamos que los detalles sean correctos
        Assertions.assertNotNull(empleado, "El detalle del empleado no debería ser nulo.");
        Assertions.assertEquals("1012345678", empleado.cedula(), "La cédula del empleado no coincide.");
        Assertions.assertEquals("Carlos", empleado.nombre(), "El nombre del empleado no coincide.");
    }
}
