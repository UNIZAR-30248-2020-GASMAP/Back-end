package com.gasmap.app.repository;

import com.gasmap.app.entity.Manager;
import com.gasmap.app.entity.Tec;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TecRepository extends CrudRepository<Tec, String> {

    @Query(value = "SELECT * FROM Tec WHERE tec_email = ?1", nativeQuery = true)
    public Tec findByEmail(String email);

    @Query(value = "SELECT * FROM Tec WHERE tec_email = ?1 AND tec_pass = ?2", nativeQuery = true)
    public Tec findByEmailAndPass(String email, String pass);

    @Query(value = "SELECT * FROM Tec", nativeQuery = true)
    public Tec[] getAllTecs();

}
