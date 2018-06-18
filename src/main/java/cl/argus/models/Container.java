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
    private String sigla;
    private String numeroContenedor;
    private String selloContenedor;
    private String digito;

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

    public String getSigla() {
        return sigla;
    }

    public String getNumeroContenedor() {
        return numeroContenedor;
    }

    public String getSelloContenedor() {
        return selloContenedor;
    }

    public String getDigito() {
        return digito;
    }
}
