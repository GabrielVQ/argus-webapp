package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Nave")
public class Nave {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "nave")
    private Set<BLMaster> blMaster;

    public long getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
        }
    
    public Set<BLMaster> getblMaster(){
        return this.blMaster;
    }
}
