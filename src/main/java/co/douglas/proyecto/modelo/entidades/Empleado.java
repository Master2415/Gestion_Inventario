package co.douglas.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    @Column(nullable = false, unique = true, length = 20) // No puede ser null, único, longitud máxima de 20 caracteres
    private String cedula;

    @Column(nullable = false, length = 50) // No puede ser null, longitud máxima de 50 caracteres
    private String nombre;

    @Column(length = 50) // Puede ser null, longitud máxima de 50 caracteres
    private String apellido;

    @Column(length = 100) // Puede ser null, longitud máxima de 100 caracteres
    private String direccion;

    @Column(length = 15) // Puede ser null, longitud máxima de 15 caracteres
    @ElementCollection
    private List<String> telefono;

    @Column(length = 50) // Puede ser null, longitud máxima de 50 caracteres
    private String email;

    @Column(nullable = true) // Puede ser null
    private Integer horasTrabajadas;

    @Column(nullable = false) // No puede ser null
    private Date fechaContratacion;

    @Column(nullable = false) // No puede ser null
    private int estado;

}
