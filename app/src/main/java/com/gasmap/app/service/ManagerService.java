package com.gasmap.app.service;

import com.gasmap.app.entity.Manager;
import org.springframework.stereotype.Service;

public interface ManagerService {
    public Manager addManager(Manager m);
    public Manager getManager(String m);
    public Manager[] getAll();

    // Given two strings that represent a certain manager's email and password
    // Returns that Manager object (used in delete Manager controller function)
    Manager getManagerWithPass(String email, String pass);

    // Given two strings that represent a certain manager's email and password
    // Returns that Manager object checking that they coincide with what is stored in the DB
    // otherwise it returns null
    public Manager loginManager(String e, String p) throws Exception;

    // Given three strings that represent a certain manager's email , name and new name
    // Returns true if it can update the managers name (check that the old name coincides)
    // otherwise it returns false
    public Boolean changeName(String email, String name, String newName) throws Exception;

    // Given three strings that represent a certain manager's email , password and new password
    // Returns true if it can update the managers password (check that the old one coincides)
    // otherwise it returns false
    public Boolean changePass(String email, String pass, String newPass) throws Exception;

    // Given an object manager it deletes that manager from the repository
    // Returns true if the manager it is deleted from the DB
    // otherwise it returns false
    public boolean deleteManager(Manager m) throws Exception;
}
