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

    @OneToMany (mappedBy = "blHouse",fetch = FetchType.EAGER)
    private Set<Cargament> cargaments;

}
