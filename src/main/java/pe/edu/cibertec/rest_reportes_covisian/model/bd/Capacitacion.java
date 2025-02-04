package pe.edu.cibertec.rest_reportes_covisian.model.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "capacitaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capacitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_capacitacion")
    private Integer idCapacitacion;
    private String tema;
    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala sala;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_lider")
    private Lider lider;
}
