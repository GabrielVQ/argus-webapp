package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Bodega")
public class Bodega {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    private String nombre;
    private String direccion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name= "Empresa_id")
    private Empresa empresa;

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public String getDireccion() {
        return direccion;
    }

}
