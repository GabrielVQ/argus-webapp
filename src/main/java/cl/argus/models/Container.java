package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Container")
public class Container {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany (mappedBy = "container", fetch = FetchType.EAGER)
    private Set<BLMaster> blMaster;
}
