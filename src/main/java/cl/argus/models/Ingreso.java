package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Ingreso")
public class Ingreso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    public String facturar;
    public String impuesto;

    @ManyToOne
    @JoinColumn(name="empresa")
    private Empresa facturara;

    @ManyToOne
    @JoinColumn(name="cobro")
    private Cobro cobro;

    public String llevarFormulario;
    public String prepaid;
    public String collect;
    public String numeroOperacion;
    public String numeroBLHouse;
    public String costo;
    @OneToOne
    @JoinColumn (name= "blHouse")
    private BLHouse blHouse;

    public long getId() {
        return id;
    }

    public String getFacturar() {
        return facturar;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public Empresa getFacturara() {
        return facturara;
    }

    public Cobro getCobro() {
        return cobro;
    }

    public String getLlevarFormulario() {
        return llevarFormulario;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public String getCollect() {
        return collect;
    }

    public BLHouse getBlHouse() {
        return blHouse;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public String getNumeroBLHouse() {
        return numeroBLHouse;
    }

    public String getCosto() {
        return costo;
    }
}