package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="BLMasters")
public class BLMaster{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private boolean blocked;
    private String servicio;
    private String nreserva;
    private String nviaje;

    @ManyToOne
    @JoinColumn(name="Naviera_id")
    private Naviera naviera;

    @OneToMany (mappedBy = "blMaster", fetch = FetchType.EAGER)
    private Set<BLHouse> blHouses;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy' a las 'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaArribo;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy' a las 'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaDespacho;

    @PrePersist
    public void onCreate() {
        this.blocked = true;
    }

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
        return this.nreserva;
    }

    public String getNviaje(){
        return this.nviaje;
    }
    public Date getFechaArribo(){
        return this.fechaArribo;
    }
    public Date getFechaDespacho(){
        return this.fechaDespacho;
    }

}
