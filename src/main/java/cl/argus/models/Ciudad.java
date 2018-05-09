package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Ciudad")
public class Ciudad {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombre;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "ciudadInicio")
    private Set<BLHouse> blHousesInicio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ciudadLlegada")
    private Set<BLHouse> blHousesLlegada;

    public String getNombre() {
        return nombre;
    }

    public long getId(){
        return id;
    }
}
