package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Nave")
public class Nave {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "nave")
    private Set<BLMaster> blMaster;




}
