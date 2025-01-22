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
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuario", nullable = false)
    private int idUsuario;

    @Column(nullable = false, length = 100, unique = true)
    private String correo;

    @Column(nullable = false, length = 100)
    private String contrasena;

    @Column(nullable = false)
    private int estado;

    @ManyToOne
    @JoinColumn(name = "id_Empleado", nullable = false)
    private Empleado empleado;

    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    private List<Caja> cajas;
}
