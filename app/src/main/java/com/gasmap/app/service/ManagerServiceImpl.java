package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.entity.Manager;
import com.gasmap.app.repository.ManagerRepository;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository repository;

    @Override
    public Manager addManager(Manager m){
        try {
            m.setPass_manager(String.valueOf(m.getPass_manager().hashCode()));
            return repository.save(m);
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Manager getManager(String email){
            return repository.findByEmail(email);
    }

    @Override
    public Manager loginManager(String email, String pass) {
        try{
            if(email == null || pass == null){
                return null;
            }
            Manager m = repository.findByEmail(email);
            if(m.getPass_manager().equals(String.valueOf(pass.hashCode()))){
                return m;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updatePass(String email, String oldPass, String newPass) throws Exception{
        try {
            Manager m = repository.findByEmailAndPass(email, oldPass);
            m.setPass_manager(newPass);
            m.setPass_manager(String.valueOf(m.getPass_manager().hashCode()));
            m = repository.save(m);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean changeName(String email, String name, String newName) throws Exception {
        try {

            Manager m = repository.findByEmail(email);

            if(!m.getName_manager().equals(name)) {
                throw new Exception("The names are not the same");
            }
            m.setName_manager(newName);
            m = repository.save(m);
            return true;
        }catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public Boolean changePass(String email, String pass, String newPass) throws Exception{

        try {
            Manager m = repository.findByEmail(email);

            if(!m.getPass_manager().equals(pass)) {
                throw new Exception("The password do not coincide");
            }

            m.setPass_manager(newPass);
            m.setPass_manager(String.valueOf(m.getPass_manager().hashCode()));
            m = repository.save(m);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteManager(Manager m) {
        try{
            repository.delete(m);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
