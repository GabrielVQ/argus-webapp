package cl.argus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Empresa")
public class Empresa {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    private int cod_agente;
    private String razon_social;
    private String nombre_abrev;
    private String nacionalidad;
    private String esNotifyAlmacenista;
    private String direccion;
    private String contacto;
    private String fonoContacto;
    

    @OneToMany (mappedBy = "empresa",fetch = FetchType.EAGER)
    private Set<Bodega> bodegas;

    @OneToOne(  fetch = FetchType.LAZY,
                mappedBy = "empresa")
    private ConfirmacionReserva confirmacion;


}
