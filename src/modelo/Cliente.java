package modelo;

import java.io.Serializable;

public class Cliente extends ObjetoGestionable implements Serializable {

    private String DNI;
    private String nombre;
    private String apellidos;
    private String telefono;
    private boolean deseaInfo;

    public Cliente(){
        this.DNI = "";
        this.nombre = "";
        this.apellidos = "";
        this.telefono = "";
        this.deseaInfo = false;
    }

    public Cliente( String DNI, String nombre, String apellidos ){
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = "";
        this.deseaInfo = false;
    }

    public Cliente( String DNI, String nombre, String apellidos, String telefono, boolean deseaInfo ){
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.deseaInfo = deseaInfo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isDeseaInfo() {
        return deseaInfo;
    }

    public void setDeseaInfo(boolean deseaInfo) {
        this.deseaInfo = deseaInfo;
    }

    @Override
    public boolean esIgual(ObjetoGestionable o2) {
        Cliente c2 = (Cliente) o2;
        return this.DNI.equals( c2.getDNI() );
    }
}
