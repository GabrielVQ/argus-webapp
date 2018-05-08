package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ConfirmacionReserva")
public class ConfirmacionReserva {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "Empresa_id", nullable = false)
    private Empresa empresa;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "BLHouse_id", nullable = false)
    private BLHouse blHouse;

}
