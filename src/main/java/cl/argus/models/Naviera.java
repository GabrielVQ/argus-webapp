package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Navieras")
public class Naviera {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy = "naviera",fetch = FetchType.EAGER)
    private Set<BLMaster> blMasters;

    public long getId(){
        return this.id;
    }
    
    public String getNombre(){
        return this.nombre;
    }
}
