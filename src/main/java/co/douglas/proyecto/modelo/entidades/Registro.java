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
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, length = 100)
    private String codigo;

    @Column(nullable = false) // 12 dígitos en total, 2 después de la coma
    private double cantidadStock;

    @Column(nullable = false) // 12 dígitos en total, 2 después de la coma
    private double precio;

    @Column(length = 100, nullable = false)
    private String fechaIngreso;

    @Column(nullable = false)
    private int estado;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
}
