package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    @OneToMany (mappedBy = "blHouse",fetch = FetchType.EAGER)
    private Set<Cargament> cargaments;

    @JsonIgnore
    @OneToOne ( mappedBy = "blHouse", fetch = FetchType.LAZY)
    private ConfirmacionReserva confirmacion;

    public long getId(){
        return id;
    }
    @JsonIgnore
    public BLMaster getBlMaster(){
        return blMaster;
    }

    public Ciudad getCiudadInicio(){
        return ciudadInicio;
    }

    public Ciudad getCiudadLlegada(){
        return ciudadLlegada;
    }

    public Set<Cargament> getCargaments(){
        return cargaments;
    }
    public ConfirmacionReserva getConfirmacion(){
        return confirmacion;
    }
}
