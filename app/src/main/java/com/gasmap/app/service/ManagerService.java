package com.gasmap.app.service;

import com.gasmap.app.entity.Manager;

public interface ManagerService {
    public Boolean createManager(Manager m) throws Exception;
    public Manager getManager(String m) throws Exception;
    public Manager loginManager(String e, String p) throws Exception;
    public Boolean updatePass(String email, String oldPass, String newPass) throws Exception;
    public Boolean changeName(String email, String name, String newName) throws Exception;
    public Boolean changePass(String email, String pass, String newPass) throws Exception;
}
