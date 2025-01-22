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
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Rol", nullable = false)
    private int idRol;

    @Column(name = "nombre_Rol", nullable = false, length = 45)
    private String nombreRol;

    @Column(nullable = false)
    private double salario;

    @OneToMany(mappedBy = "rol")
    private List<Empleado> empleados;
}
