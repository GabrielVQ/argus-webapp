package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Ciudad")
public class Ciudad {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "ciudadInicio")
    private Set<BLHouse> blHousesInicio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ciudadLlegada")
    private Set<BLHouse> blHousesLlegada;
    
    private String nombre;
    
    public String getNombre() {
        return nombre;
    }

    public String setNombre(String nombre) {
        return this.nombre=nombre;
    }

    public long getId(){
        return id;
    }
}
