package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Bodega")
public class Bodega {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn (name= "Empresa_id")
    private Empresa empresa;


}
