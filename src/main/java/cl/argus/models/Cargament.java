package cl.argus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "Cargament")
public class Cargament {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    private String generalImo;
    
    @NotNull
    private String imoClase;
    
    private int imoNumero;
    private int peso;
    private int volumen;
    private String tipo_bulto;
    

    @ManyToOne
    @JoinColumn (name= "BLHouse_id")
    private BLHouse blHouse;

    public long getId() {
        return id;
    }

    public BLHouse getBlHouse() {
        return blHouse;
    }

    public int getImoNumero() {
        return imoNumero;
    }

    public int getPeso() {
        return peso;
    }

    public int getVolumen() {
        return volumen;
    }

    public String getGeneralImo() {
        return generalImo;
    }

    public String getImoClase() {
        return imoClase;
    }

    public String getTipo_bulto() {
        return tipo_bulto;
    }
}
