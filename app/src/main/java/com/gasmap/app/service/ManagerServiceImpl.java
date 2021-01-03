package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.entity.Manager;
import com.gasmap.app.repository.GasRepository;
import com.gasmap.app.repository.ManagerRepository;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerRepository repository;

    @Autowired
    GasRepository gasRepository;

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public Manager addManager(Manager m){
        try {
            System.out.println("Manager pass: " + m.getPass_manager());
            m.setPass_manager(String.valueOf(m.getPass_manager().hashCode()));
            System.out.println("Manager pass hash: " + m.getPass_manager());
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
    public Manager[] getAll() {
        return repository.getAllManagers();
    }

    @Override
    public Manager getManagerWithPass(String email, String pass){
        return repository.findByEmailAndPass(email,String.valueOf(pass.hashCode()));
    }

    @Override
    public Manager loginManager(String email, String pass) {
        try{
            if(email == null || pass == null){
                return null;
            }
            Manager m = repository.findByEmail(email);
            if(m == null){
               System.out.println("Error, no se ha encontrado Manager");
            }
            System.out.println("Email Manager: " + m.getEmail());
            System.out.println("Pass Manager: " + m.getPass_manager());
            System.out.println("Email parameter: " + email);
            System.out.println("Pass: " + pass);
            System.out.println("Hashed pass: " + pass.hashCode());
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
    public Boolean changeName(String email, String name, String newName) throws Exception {
        try {

            Manager m = repository.findByEmail(email);

            if(!m.getName_manager().equals(name)) {
                throw new Exception("The names are not the same");
            }
            m.setName_manager(newName);
            repository.save(m);
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


            if(!m.getPass_manager().equals(String.valueOf(pass.hashCode()))) {
                throw new Exception("The password do not coincide");
            }

            m.setPass_manager(newPass);
            m.setPass_manager(String.valueOf(m.getPass_manager().hashCode()));
            repository.save(m);
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

    @Override
    public Boolean sendReport(int id, String message){
        try {
            Gas g = gasRepository.findById(id);
            Manager m = g.getManager();
            if(m == null || m.equals(new Manager())){
                throw new Exception("Gas has not Manager");
            }
            String subject = "GasMap, your Gas Station has received a report";
            SimpleMailMessage sendMessage = new SimpleMailMessage();
            sendMessage.setFrom("GasMap.Reports@gmail.com");
            sendMessage.setTo(m.getEmail());
            sendMessage.setSubject(subject);
            sendMessage.setText(message);
            emailSender.send(sendMessage);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
