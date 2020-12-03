package com.gasmap.app.service;

import com.gasmap.app.entity.Tec;
import com.gasmap.app.repository.GasRepository;
import com.gasmap.app.repository.TecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecServiceImpl implements TecService {

    @Autowired
    TecRepository trepository;

    @Autowired
    public TecServiceImpl(TecRepository repository) {
        this.trepository = repository;
    }

    @Override
    public Boolean login(String email, String pass) {
        try{
            Tec t = trepository.findByEmail(email);
            return t.getPass().equals(pass);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
