package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "BLHouse")
public class BLHouse {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name="blMaster")
    private BLMaster blMaster;

    @ManyToOne
    @JoinColumn(name="ciudad_inicio")
    private Ciudad ciudadInicio;

    @ManyToOne
    @JoinColumn(name="ciudadLlegada")
    private Ciudad ciudadLlegada;

    @JsonIgnore
    @OneToMany (mappedBy = "blHouse",fetch = FetchType.EAGER)
    private Set<Cargament> cargaments;

    @JsonIgnore
    @OneToOne ( mappedBy = "blHouse", fetch = FetchType.LAZY)
    private ConfirmacionReserva confirmacion;
//
    private String tipo;

    @ManyToOne
    @JoinColumn(name="shipper")
    private Empresa shipper;

    @ManyToOne
    @JoinColumn(name="clienteExtranjero")
    private Empresa clienteExtranjero;

    @ManyToOne
    @JoinColumn(name="notify")
    private Empresa notify;

    private String numeroOperacion;

    private String ppcc;

    private String carga;

    private String imoClase;

    private String imoNumero;

    private String kilos;

    private String volumen;

    private String moneda;

    private int numeroBLHouse;

    @ManyToOne
    @JoinColumn(name="almacenista")
    private Empresa almacenista;

    private String preCarriage;

    private String lugarRecepcion;

    private String contacto;

    private String telefono;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy' a las 'HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaStacking;

    private String observacion;

    private String flete;

    private String bultos;

    private String tipoMovimiento;

    private String tipoHouse;
    //Getters

    public long getId(){
        return id;
    }
    @JsonIgnore
    public BLMaster getBlMaster(){
        return blMaster;
    }

    public Ciudad getCiudadInicio(){
        return ciudadInicio;
    }

    public Ciudad getCiudadLlegada(){
        return ciudadLlegada;
    }

    public Set<Cargament> getCargaments(){
        return cargaments;
    }

    public ConfirmacionReserva getConfirmacion(){
        return confirmacion;
    }

    public Empresa getShipper() {
        return shipper;
    }

    public Empresa getClienteExtranjero() {
        return clienteExtranjero;
    }

    public Empresa getNotify() {
        return notify;
    }

    public String getPpcc() {
        return ppcc;
    }

    public String getCarga() {
        return carga;
    }

    public String getImoClase() {
        return imoClase;
    }

    public String getImoNumero() {
        return imoNumero;
    }

    public String getKilos() {
        return kilos;
    }

    public String getVolumen() {
        return volumen;
    }

    public Empresa getAlmacenista() {
        return almacenista;
    }

    public String getPreCarriage() {
        return preCarriage;
    }

    public String getLugarRecepcion() {
        return lugarRecepcion;
    }

    public String getContacto() {
        return contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFechaStacking() {
        return fechaStacking;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFlete() {
        return flete;
    }

    public String getBultos() {
        return bultos;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getTipoHouse() {
        return tipoHouse;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public int getNumeroBLHouse() {
        return numeroBLHouse;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setBlMaster(BLMaster blMaster) {
        this.blMaster = blMaster;
    }

    public void setCiudadInicio(Ciudad ciudadInicio) {
        this.ciudadInicio = ciudadInicio;
    }

    public void setCiudadLlegada(Ciudad ciudadLlegada) {
        this.ciudadLlegada = ciudadLlegada;
    }

    public void setCargaments(Set<Cargament> cargaments) {
        this.cargaments = cargaments;
    }

    public void setConfirmacion(ConfirmacionReserva confirmacion) {
        this.confirmacion = confirmacion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setShipper(Empresa shipper) {
        this.shipper = shipper;
    }

    public void setClienteExtranjero(Empresa clienteExtranjero) {
        this.clienteExtranjero = clienteExtranjero;
    }

    public void setNotify(Empresa notify) {
        this.notify = notify;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public void setPpcc(String ppcc) {
        this.ppcc = ppcc;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public void setImoClase(String imoClase) {
        this.imoClase = imoClase;
    }

    public void setImoNumero(String imoNumero) {
        this.imoNumero = imoNumero;
    }

    public void setKilos(String kilos) {
        this.kilos = kilos;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setNumeroBLHouse(int numeroBLHouse) {
        this.numeroBLHouse = numeroBLHouse;
    }

    public void setAlmacenista(Empresa almacenista) {
        this.almacenista = almacenista;
    }

    public void setPreCarriage(String preCarriage) {
        this.preCarriage = preCarriage;
    }

    public void setLugarRecepcion(String lugarRecepcion) {
        this.lugarRecepcion = lugarRecepcion;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaStacking(Date fechaStacking) {
        this.fechaStacking = fechaStacking;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setFlete(String flete) {
        this.flete = flete;
    }

    public void setBultos(String bultos) {
        this.bultos = bultos;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public void setTipoHouse(String tipoHouse) {
        this.tipoHouse = tipoHouse;
    }
}
