package co.douglas.proyecto.modelo.entidades;

import co.douglas.proyecto.modelo.enumeraciones.TipoProducto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class    Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int idProducto;

    @Column(nullable = false, length = 100, unique = true)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    @Column(nullable = false) // 12 dígitos en total, 2 después de la coma
    private double utilidad;

    @Column(nullable = false) // 12 dígitos en total, 2 después de la coma
    private double iva;

    @Column(nullable = false) // 12 dígitos en total, 2 después de la coma
    private double precioTotal;

    @Column(nullable = false) // 12 dígitos en total, 2 después de la coma
    private double stock;

    @Column(name = "precio_neto"    ) // 12 dígitos en total, 2 después de la coma
    private double precioNeto;

    @Column(nullable = false)
    private int estado;

    @OneToMany(mappedBy = "producto")
    private List<Compra> compras;

    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> detalleVentas;

    @OneToMany(mappedBy = "producto")
    private List<DetalleCompra> detalleCompras;

}
