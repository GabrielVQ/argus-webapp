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
    
    private String direccion;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "puertoOrigen")
    private Set<BLMaster> blMasterInicio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "puertoDescarga")
    private Set<BLMaster> blMasterLlegada;

    public long getId(){
        return this.id;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getDireccion(){
        return this.direccion;
    }
    
    public Set<BLMaster> getBLMasterInicio(){
        return this.blMasterInicio;
    }
    
    public Set<BLMaster> getblMasterLlegada(){
        return this.blMasterLlegada;
    }
}
