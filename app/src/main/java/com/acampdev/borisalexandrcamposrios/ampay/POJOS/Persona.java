package com.acampdev.borisalexandrcamposrios.ampay.POJOS;

public class Persona {


    private String nombres;
    private String address;
    private String parentesco;
    private int dni;
    private int telefono;


    public Persona(){
        super();
    }

    public Persona(String nombres, String address, String parentesco, int telefono, int dni) {
        this.nombres = nombres;
        this.address = address;
        this.parentesco = parentesco;
        this.telefono = telefono;
        this.dni = dni;


    }



    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getDir() {
        return address;
    }

    public void setDir(String dir) {
        this.address = dir;
    }
    public int getTelefono() {
        return telefono;
    }
    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }







}
