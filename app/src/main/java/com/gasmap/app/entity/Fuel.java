package com.gasmap.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//Entity Gas Station with its attributes and relationships.
@Entity
@IdClass(FuelId.class)
@Table(name = "Fuel")
public class Fuel implements Serializable {

    @Id
    @Column(name = "id_fuel", length = 50)
    String id_fuel;

    @Id
    @Column(name = "id_gas_fuel")
    Integer id_gas;

    @Column(name = "price_fuel")
    double price_fuel;


    public Fuel() {
    }

    public Fuel(String id_fuel) {
        this.id_fuel = id_fuel;
    }

    public Fuel(String id_fuel, Double price_fuel, Integer id_gas) {
        this.id_fuel = id_fuel;
        this.price_fuel = price_fuel;
        this.id_gas = id_gas;
    }

    public String getId_fuel() {
        return id_fuel;
    }

    public void setId_fuel(String id_fuel) {
        this.id_fuel = id_fuel;
    }

    public Double getPrice_fuel() {
        return price_fuel;
    }

    public void setPrice_fuel(Double price_fuel) {
        this.price_fuel = price_fuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuel fuel = (Fuel) o;
        return Objects.equals(id_fuel, fuel.id_fuel) &&
                Objects.equals(price_fuel, fuel.price_fuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_fuel, price_fuel);
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "id_fuel='" + id_fuel + '\'' +
                ", price_fuel=" + price_fuel +
                '}';
    }
}