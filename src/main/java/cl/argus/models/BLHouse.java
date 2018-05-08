package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BLHouse")
public class BLHouse {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name="BLMaster_id")
    private BLMaster blMaster;

    @ManyToOne
    @JoinColumn(name="ciudad_inicio")
    private Ciudad ciudadInicio;

    @ManyToOne
    @JoinColumn(name="Ciudad_llegada")
    private Ciudad ciudadLlegada;

    @OneToMany (mappedBy = "blHouse",fetch = FetchType.EAGER)
    private Set<Cargament> cargaments;

    @OneToOne ( mappedBy = "blHouse", fetch = FetchType.LAZY)
    private ConfirmacionReserva confirmacion;

}
