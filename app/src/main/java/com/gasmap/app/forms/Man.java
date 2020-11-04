package com.gasmap.app.forms;

public class Man{

    public String email_manager;
    public String pass_manager;
    public String name_manager;
    public String phone_manager;
    public String gas_manager;

    public Man(){
    }

    public Man(String email, String pass, String name_manager,
               String phone_manager, String gas_manager){
        this.email_manager = email;
        this.pass_manager = pass;
        this.name_manager = name_manager;
        this.phone_manager = phone_manager;
        this.gas_manager = gas_manager;
    }

    public String getEmail_manager() {
        return email_manager;
    }

    public void setEmail_manager(String email_manager) {
        this.email_manager = email_manager;
    }

    public String getPass_manager() {
        return pass_manager;
    }

    public void setPass_manager(String pass_manager) {
        this.pass_manager = pass_manager;
    }

    public String getName_manager() {
        return name_manager;
    }

    public void setName_manager(String name_manager) {
        this.name_manager = name_manager;
    }

    public String getPhone_manager() {
        return phone_manager;
    }

    public void setPhone_manager(String phone_manager) {
        this.phone_manager = phone_manager;
    }

    public String getGas_manager() {
        return gas_manager;
    }

    public void setGas_manager(String gas_manager) {
        this.gas_manager = gas_manager;
    }
};
