package com.gasmap.app.repository;

import com.gasmap.app.entity.Gas;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface GasRepository extends CrudRepository<Gas, Integer>{
    @Query(value = "SELECT * FROM ZZGas WHERE id_gas = ?1", nativeQuery = true)
    public Gas findById(int i);

}
