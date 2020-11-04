package com.gasmap.app.repository;

import com.gasmap.app.entity.Manager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager,String> {
    @Query(value = "SELECT * FROM Manager WHERE email = ?1", nativeQuery = true)
    public Manager findByEmail(String email);

    @Query(value = "SELECT * FROM Manager WHERE email = ?1 AND pass = ?2", nativeQuery = true)
    public Manager findByEmailAndPass(String email, String pass);

    @Query(value = "SELECT * FROM Manager", nativeQuery = true)
    public Manager[] getAllManagers();
}
