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
    private String selloEmpresa;
    private String selloCliente;
    private String selloAduana;
    private String digito;
    private String descripcionLarga;

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

    public String getSelloEmpresa() {
        return selloEmpresa;
    }

    public String getSelloCliente() {
        return selloCliente;
    }

    public String getSelloAduana() {
        return selloAduana;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public String getDigito() {
        return digito;
    }
}
