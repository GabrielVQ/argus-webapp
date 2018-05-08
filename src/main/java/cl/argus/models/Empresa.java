package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Empresa")
public class Empresa {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany (mappedBy = "empresa",fetch = FetchType.EAGER)
    private Set<Bodega> bodegas;

    @OneToOne(  fetch = FetchType.LAZY,
                mappedBy = "empresa")
    private ConfirmacionReserva confirmacion;


}
