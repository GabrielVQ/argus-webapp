package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ConfirmacionReserva")
public class ConfirmacionReserva {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    

    @ManyToOne
    @JoinColumn(name = "Empresa_id", nullable = false)
    private Empresa empresa;

    @JsonIgnore
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "BLHouse_id", nullable = false)
    private BLHouse blHouse;
    
    private String estado;
    private boolean confirmado;

    public BLHouse getBlHouse() {
        return blHouse;
    }

    public long getId() {
        return id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getEstado() {
        return estado;
    }

}
