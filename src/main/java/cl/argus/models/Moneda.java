/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.argus.models;

import javax.persistence.*;
import java.util.Set;
/**
 *
 * @author Victor
 */
@Entity
@Table(name = "Moneda")
public class Moneda {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    private String nombre;
    private float valor_clp;
    
    public long getId(){
        return this.id;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public float valor_clp(){
        return this.valor_clp;
    }
    
    
}
