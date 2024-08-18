package co.douglas.proyecto.modelo.entidades;

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
public class Proveedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor", nullable = false)
    private int idProveedor;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo_proveedor", length = 100)
    private String tipoProveedor;

    @ElementCollection
    @CollectionTable(name = "proveedor_telefonos", joinColumns = @JoinColumn(name = "idProveedor"))
    @Column(name = "telefono", nullable = false)
    private List<String> telefonos;

    @Column(length = 100)
    private String direccion;

    @Column(length = 100)
    private String correo;

    @Column(nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "proveedor")
    private List<Registro> registros;
}

