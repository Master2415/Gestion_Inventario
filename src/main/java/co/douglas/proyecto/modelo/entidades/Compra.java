package co.douglas.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompra", nullable = false)
    private int idCompra;


    @Column(nullable = false)
    private double cantidadStock;

    @Column(nullable = false)
    private double precio;

    @Column(length = 100, nullable = false)
    private LocalDate fecha_Ingreso;

    @Column(nullable = false)
    private int estado;

    @OneToMany(mappedBy = "compra")
    private List<DetalleCompra> detalleCompras;

    @ManyToOne
    @JoinColumn(name = "id_Producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_Proveedor", nullable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = false)
    private Usuario usuario;

}
