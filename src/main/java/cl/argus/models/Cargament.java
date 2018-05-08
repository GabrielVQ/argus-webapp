package cl.argus.models;

import javax.persistence.*;

@Entity
@Table (name = "Cargament")
public class Cargament {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn (name= "BLHouse_id")
    private BLHouse blHouse;


}
