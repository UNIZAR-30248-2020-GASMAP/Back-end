package com.gasmap.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "Tec")
public class Tec {

    @Id
    @Column(name = "tec_email", nullable = false,length=50)
    @NotBlank
    @Email
    private String email;

    @Column(name = "tec_pass", nullable = false,length=50)
    @NotBlank
    private String pass;

    public Tec() {
    }

    public Tec(@NotBlank @Email String email, @NotBlank String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tec tec = (Tec) o;
        return Objects.equals(email, tec.email) &&
                Objects.equals(pass, tec.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, pass);
    }

    @Override
    public String toString() {
        return "Tec{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
