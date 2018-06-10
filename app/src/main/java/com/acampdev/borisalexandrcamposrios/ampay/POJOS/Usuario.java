package com.acampdev.borisalexandrcamposrios.ampay.POJOS;

public class Usuario {

    private String user;
    private String pass;
    private String email;
    private String nombres;

    public  Usuario(){}

    private String idUser;

    public Usuario(String idUser, String user, String pass, String email, String nombres) {
        this.idUser = idUser;
        this.user = user;
        this.pass = pass;
        this.email = email;
        this.nombres = nombres;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }






}
