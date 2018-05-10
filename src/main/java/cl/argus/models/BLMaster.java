package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="BLMaster")
public class BLMaster{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String numeroOperacion;
    private boolean blocked;
    private String servicio;
    private String nReserva;
    private String nViaje;
    private String agenteCreador;
    private String tipoNegocio;
    private String destino;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy' a las 'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaIngreso;



    @ManyToOne
    @JoinColumn(name="Naviera_id")
    private Naviera naviera;


    @ManyToOne
    @JoinColumn(name="Container_id")
    private Container container;


    @OneToMany (mappedBy = "blMaster", fetch = FetchType.EAGER)
    private Set<BLHouse> blHouses;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy' a las 'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaLlegada;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy' a las 'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaZarpe;

    @ManyToOne
    @JoinColumn(name="puerto_origen")
    private Puerto puertoOrigen;

    @ManyToOne
    @JoinColumn(name="nave")
    private Nave nave;

    @ManyToOne
    @JoinColumn(name="puerto_descarga")
    private Puerto puertoDescarga;

    //@PrePersist
   // public void onCreate() {
   //     this.blocked = true;
    //}

    public BLMaster(){}

    public long getId() {
        return id;
    }

    public String getnReserva() {
        return nReserva;
    }

    public String getDestino() {
        return destino;
    }

    public String getAgenteCreador() {
        return agenteCreador;
    }

    public Set<BLHouse> getBlHouses() {
        return blHouses;
    }

    public Puerto getPuertoOrigen() {
        return puertoOrigen;
    }

    public Puerto getPuertoDescarga() {
        return puertoDescarga;
    }

    public Naviera getNaviera() {
        return naviera;
    }

    public Nave getNave() {
        return nave;
    }

    public Container getContainer() {
        return container;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public Date getFechaZarpe() {
        return fechaZarpe;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public String getnViaje() {
        return nViaje;
    }

    public String getServicio() {
        return servicio;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setAgenteCreador(String agenteCreador) {
        this.agenteCreador = agenteCreador;
    }

}
