package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
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
//
    private String tipo;

    @ManyToOne
    @JoinColumn(name="shipper")
    private Empresa shipper;

    @ManyToOne
    @JoinColumn(name="clienteExtranjero")
    private Empresa clienteExtranjero;

    @ManyToOne
    @JoinColumn(name="notify")
    private Empresa notify;

    private String ppcc;

    private String carga;

    private String imoClase;

    private String imoNumero;

    private String kilos;

    private String volumen;

    @ManyToOne
    @JoinColumn(name="almacenista")
    private Empresa almacenista;

    private String preCarriage;

    private String lugarRecepcion;

    private String contacto;

    private String telefono;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy' a las 'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaStacking;

    private String observacion;

    private String flete;

    private String bultos;

    private String tipoMovimiento;

    private String tipoHouse;
    //Getters

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

    public Empresa getShipper() {
        return shipper;
    }

    public Empresa getClienteExtranjero() {
        return clienteExtranjero;
    }

    public Empresa getNotify() {
        return notify;
    }

    public String getPpcc() {
        return ppcc;
    }

    public String getCarga() {
        return carga;
    }

    public String getImoClase() {
        return imoClase;
    }

    public String getImoNumero() {
        return imoNumero;
    }

    public String getKilos() {
        return kilos;
    }

    public String getVolumen() {
        return volumen;
    }

    public Empresa getAlmacenista() {
        return almacenista;
    }

    public String getPreCarriage() {
        return preCarriage;
    }

    public String getLugarRecepcion() {
        return lugarRecepcion;
    }

    public String getContacto() {
        return contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFechaStacking() {
        return fechaStacking;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFlete() {
        return flete;
    }

    public String getBultos() {
        return bultos;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getTipoHouse() {
        return tipoHouse;
    }
}
