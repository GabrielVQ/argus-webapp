package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    public Set<BLMaster> getBlMaster() {
        return blMaster;
    }

    public String getTipo() {
        return tipo;
    }

}
