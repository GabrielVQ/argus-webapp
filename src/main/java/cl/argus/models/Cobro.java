package cl.argus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Cobro")
public class Cobro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String nombreCobro;

    public long getId() {
        return id;
    }

    public String getNombreCobro() {
        return nombreCobro;
    }
}