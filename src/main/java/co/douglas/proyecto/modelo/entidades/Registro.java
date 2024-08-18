package co.douglas.proyecto.modelo.entidades;

import co.douglas.proyecto.modelo.enumeraciones.TipoProducto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Column(nullable = false, length = 100, unique = true)
    private String codigo;

    @Column(nullable = false)
    private float cantidadStock;

    @Column(nullable = false)
    private float precio;

    @Column(length = 100)
    private String fechaIngreso;

    @Column(nullable = true)
    private Integer idProductoStock;

    @Column(nullable = true)
    private Integer idProveedor;

    @Column(nullable = false)
    private int estado;

    @Enumerated(EnumType.STRING) // Guarda el nombre del enum como string en la base de datos
    @Column(nullable = false)
    private TipoProducto tipoProducto;
}
