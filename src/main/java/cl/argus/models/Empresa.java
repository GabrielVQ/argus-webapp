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

    private String codigo;
    private String razon_social;
    private String nombreAbrev;
    private String nacionalidad;
    private String esNotifyAlmacenista;
    private String direccion;
    private String contacto;
    private String fonoContacto;
    private String rut;
    private String tipoEmpresa;



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
    public String getRut(){return rut;}

    public String getNombreAbrev() {
        return nombreAbrev;
    }
    public String getRazon_social(){
        return razon_social;
    }
    public String getNacionalidad(){
        return nacionalidad;
    }
    public String getCod_agente() {
        return codigo;
    }
    public String getContacto() {return contacto;}
    public String getFonoContacto() {return fonoContacto;}
    public Set<Bodega> getBodegas() {
        return bodegas;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setFonoContacto(String fonoContacto) {
        this.fonoContacto = fonoContacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEsNotifyAlmacenista(String esNotifyAlmacenista) {
        this.esNotifyAlmacenista = esNotifyAlmacenista;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setNombre_abrev(String nombreAbrev) {
        this.nombreAbrev = nombreAbrev;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getEsNotifyAlmacenista() {
        return esNotifyAlmacenista;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }
}
