package modelo;

import java.io.Serializable;

public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ID;
    private String descripcion;

    public Seccion(){
        this.ID = "";
        this.descripcion = "";
    }

    public Seccion(String ID, String descripcion){
        this.ID = ID;
        this.descripcion = descripcion;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Seccion s2 = (Seccion) obj;

        if (this.ID == null){
            return s2.ID == null;
        }

        return this.ID.equals(s2.ID);
    }

}