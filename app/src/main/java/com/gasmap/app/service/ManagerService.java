package com.gasmap.app.service;

import com.gasmap.app.entity.Manager;
import org.springframework.stereotype.Service;

public interface ManagerService {

    // Given an object Manager
    // Returns the save result after adding the manager to the DB
    public Manager addManager(Manager m);

    // Given two strings that represent a certain manager's email
    // Returns that Manager object checking that the email coincides with what is stored in the DB
    public Manager getManager(String m);

    // Returns an array with all the managers stored in the DB
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
