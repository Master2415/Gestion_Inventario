package co.douglas.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DetalleVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false)
    private double cantidad;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "Venta_idVenta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_idProducto", nullable = false)
    private Producto producto;
}
