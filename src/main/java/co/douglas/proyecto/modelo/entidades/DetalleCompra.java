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
public class DetalleCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleCompra", nullable = false)
    private int idDetalleCompra;

    @Column(nullable = false)
    private double cantidad;

    @Column(nullable = false)
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "compra_idCompra", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_idProducto", nullable = false)
    private Producto producto;
}
