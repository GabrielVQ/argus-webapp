package cl.argus.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Userdb")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String rut;
    private String email;
    private String phone;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getRut() {
        return rut;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}

    public User(String name,
                 String rut,
                 String email,
                 String phone,
                 String password) {
        this.name = name;
        this.rut = rut;
        this.email = email;
        this.phone = phone;
    }
}