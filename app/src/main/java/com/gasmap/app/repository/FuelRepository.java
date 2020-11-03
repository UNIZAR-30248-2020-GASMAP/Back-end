package com.gasmap.app.repository;

import com.gasmap.app.entity.Fuel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface FuelRepository extends CrudRepository<Fuel, String>{

    @Query(value = "SELECT * FROM Fuel WHERE id_fuel = ?1", nativeQuery = true)
    public Fuel findById(int i);

    @Query(value = "SELECT * FROM Fuel", nativeQuery = true)
    public Fuel[] findAllFuels();

}