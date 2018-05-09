package cl.argus.models;

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


}
