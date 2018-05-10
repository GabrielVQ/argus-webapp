package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Container")
public class Container {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany (mappedBy = "container", fetch = FetchType.EAGER)
    private Set<BLMaster> blMaster;
    
    private String tipo;

    public long getId() {
        return id;
    }

    public Set<BLMaster> getBlMaster() {
        return blMaster;
    }

    public String getTipo() {
        return tipo;
    }

}
