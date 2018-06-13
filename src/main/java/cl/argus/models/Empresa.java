package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @OneToMany( mappedBy = "empresa",fetch = FetchType.EAGER)
    private Set<ConfirmacionReserva> confirmacion;

    public long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public Set<ConfirmacionReserva> getConfirmacion() {
        return confirmacion;
    }

    public String getNombre_abrev() {
        return nombre_abrev;
    }
    public String getRazon_social(){
        return razon_social;
    }
    public String getNacionalidad(){
        return nacionalidad;
    }
    public int getCod_agente() {
        return cod_agente;
    }
    public String getContacto() {return contacto;}
    public String getFonoContacto() {return fonoContacto;}
    public Set<Bodega> getBodegas() {
        return bodegas;
    }
}
