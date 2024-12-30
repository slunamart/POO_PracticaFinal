package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String DNI;
    private String nombre;
    private String apellidos;
    private String telefono;
    private boolean deseaInfo;

    public Cliente(String DNI, String nombre, String apellidos){
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = "";
        this.deseaInfo = false;
    }

    public Cliente(String DNI, String nombre, String apellidos, String telefono, boolean deseaInfo){
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.deseaInfo = deseaInfo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setDeseaInfo(Boolean deseaInfo){
        this.deseaInfo = deseaInfo;
    }

    public boolean getDeseaInfo() {
        return deseaInfo;
    }



}








