package com.gasmap.app.service;

import com.gasmap.app.entity.Manager;
import org.springframework.stereotype.Service;

public interface ManagerService {
    public Manager addManager(Manager m);
    public Manager getManager(String m);

    Manager getManagerWithPass(String email, String pass);

    public Manager loginManager(String e, String p) throws Exception;
    public Boolean updatePass(String email, String oldPass, String newPass) throws Exception;
    public Boolean changeName(String email, String name, String newName) throws Exception;
    public Boolean changePass(String email, String pass, String newPass) throws Exception;
    public boolean deleteManager(Manager m) throws Exception;
}
