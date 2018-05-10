package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Nave")
public class Nave {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombre;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "nave")
    private Set<BLMaster> blMaster;




}
