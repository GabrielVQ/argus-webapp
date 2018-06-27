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

    @NotNull
    private String imoClase;

    @ManyToOne
    @JoinColumn(name="contenedor")
    private Container contenedor;

    private String markNumbers;

    private String numerPackages;

    private String groosWeight;

    private String measurement;

    private String descriptionGoods;

    private String numeroOperacion;

    @OneToOne
    @JoinColumn (name= "blHouse")
    private BLHouse blHouse;

    public long getId() {
        return id;
    }

    public String getImoClase() {
        return imoClase;
    }

    public Container getContenedor() {
        return contenedor;
    }

    public String getMarkNumbers() {
        return markNumbers;
    }

    public String getNumerPackages() {
        return numerPackages;
    }

    public String getGroosWeight() {
        return groosWeight;
    }

    public String getMeasurement() {
        return measurement;
    }

    public BLHouse getBlHouse() {
        return blHouse;
    }

    public String getDescriptionGoods() {
        return descriptionGoods;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }
}
