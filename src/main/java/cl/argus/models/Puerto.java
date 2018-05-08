package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Puerto")
public class Puerto {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombre;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "puertoInicio")
    private Set<BLMaster> blMasterInicio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "puertoLlegada")
    private Set<BLMaster> blMasterLlegada;


}
