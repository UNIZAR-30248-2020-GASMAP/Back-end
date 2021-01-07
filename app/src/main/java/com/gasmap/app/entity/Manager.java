package com.gasmap.app.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "Manager")
public class Manager {
    @Id
    @Column(name = "email", nullable = false,length=100)
    @NotBlank
    @Email
    private String email_manager;

    @Column(name = "name", nullable = false,length=100)
    @NotBlank
    private String name_manager;

    //private int id_gas_manager;

    @Column(name = "phone", nullable = false, length = 10)
    @Pattern(regexp = "^[689]{1}[0-9]{8}$", message = "invalid phone number!")
    private int phone_manager;

    @Column(name = "pass", nullable = false,length=100)
    @NotBlank
    private String pass_manager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_gas_manager", referencedColumnName = "id_gas")
    private Gas gas;

    public Manager() {
    }

    public Manager(@NotBlank @Email String email_manager, @NotBlank String name_manager, @Pattern(regexp = "^[679]{1}[0-9]{8}$", message = "invalid phone number!") int phone_manager, @NotBlank String pass_manager, Gas gas) {
        this.email_manager = email_manager;
        this.name_manager = name_manager;
        this.phone_manager = phone_manager;
        this.pass_manager = pass_manager;
        this.gas = gas;
    }


    public String getEmail() {
        return email_manager;
    }
    public void setEmail_manager(String email_manager) {
        this.email_manager = email_manager;
    }

    public String getName_manager() {
        return name_manager;
    }

    public void setName_manager(String name_manager) {
        this.name_manager = name_manager;
    }
    /*
    public int getPhone_manager() {
        return phone_manager;
    }
    */
    public void setPhone_manager(int phone_manager) {
        this.phone_manager = phone_manager;
    }



    public String getPass_manager() {
        return pass_manager;
    }

    public void setPass_manager(String pass_manager) {
        this.pass_manager = pass_manager;
    }

    public Gas getGas() {
        return gas;
    }


    public void setGas(Gas gas) {
        this.gas = gas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return phone_manager == manager.phone_manager &&
                Objects.equals(email_manager, manager.email_manager) &&
                Objects.equals(name_manager, manager.name_manager) &&
                Objects.equals(pass_manager, manager.pass_manager);
    }

    /*
    @Override
    public int hashCode() {
        return Objects.hash(email_manager, name_manager, phone_manager, pass_manager);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "email_manager='" + email_manager + '\'' +
                ", name_manager='" + name_manager + '\'' +
                ", phone_manager=" + phone_manager +
                ", pass_manager='" + pass_manager + '\'' +
                ", " + this.gas.toString() + '\'' +
                '}';
    }

     */
}
