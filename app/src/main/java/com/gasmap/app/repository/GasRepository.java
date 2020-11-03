package com.gasmap.app.repository;

import com.gasmap.app.entity.Gas;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface GasRepository extends CrudRepository<Gas, Integer>{
    @Query(value = "SELECT * FROM ZZGas WHERE id_gas = ?1", nativeQuery = true)
    public Gas findById(int i);

    @Query(value = "SELECT * FROM ZZGas WHERE street_gas = ?1", nativeQuery = true)
    public Gas findByStreet(String street);

    @Query(value = "SELECT * FROM ZZGas WHERE latitude_gas =?1 AND longitude_gas = ?2", nativeQuery = true)
    public Gas findByLatAndLon(double lat, double lon);

    @Query(value = "SELECT * FROM ZZGas WHERE gps.fnCalcDistanceKM(latitude_gas,?1,longitude_gas,?2) < 50.0 ", nativeQuery = true)
    public Gas[] findByDistance(double lat2, double lon2);

    @Query(value = "SELECT * FROM ZZGas", nativeQuery = true)
    public Gas[] findAllGasStations();

}
