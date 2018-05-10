package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    public long getId(){
        return this.id;
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    public String getServicio(){
        return this.servicio;
    }

    public String getNreserva() {
        return this.nReserva;
    }

    public String getNviaje(){
        return this.nViaje;
    }
    public Date getFechaLlegada(){
        return this.fechaLlegada;
    }
    public Date getFechaZarpe(){
        return this.fechaZarpe;
    }

}
