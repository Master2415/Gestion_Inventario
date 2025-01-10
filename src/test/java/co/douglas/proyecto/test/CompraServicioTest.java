package co.douglas.proyecto.test;

import co.douglas.proyecto.dto.compra.DetalleCompraDTO;
import co.douglas.proyecto.dto.compra.GuardarCompraDTO;
import co.douglas.proyecto.servicios.interfaces.CompraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

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
                15);

        //guardamos la compra usando el servicio
        DetalleCompraDTO newBuy = compraServicio.guardarCompra(guardarCompraDTO);
        Assertions.assertNotNull(newBuy);
    }
}
