package cl.argus.models;


import javax.persistence.*;

@Entity
@Table(name = "Container")
public class Container {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name="naviera")
    private Naviera naviera;
}
