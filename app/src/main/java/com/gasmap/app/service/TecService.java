package com.gasmap.app.service;

public interface TecService {

    // Given two strings that represent a certain gasmap technician's email and password
    // Returns true checking that the email exists and the pass coincide with the one stored in the DB
    // otherwise it returns false
    Boolean login(String email, String pass);

}
