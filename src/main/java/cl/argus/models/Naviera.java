package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Navieras")
public class Naviera {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nombre;

    @OneToMany(mappedBy = "naviera",fetch = FetchType.EAGER)
    private Set<BLMaster> blMasters;
}
