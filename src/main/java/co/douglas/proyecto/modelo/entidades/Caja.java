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
public class Caja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Caja", nullable = false)
    private int idCaja;

    @Column(nullable = false, length = 100)
    private String horaInicio;

    @Column(nullable = false, length = 100)
    private String horaFin;

    @Column(nullable = false)
    private double montoApertura;

    @Column(nullable = false)
    private double montoFin;

    @Column(nullable = false)
    private int estado;

    @ManyToOne
    @JoinColumn(name = "id_User", nullable = false)
    private Usuario usuario;
}
