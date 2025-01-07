package modelo;

import java.io.Serializable;

/**
 * Clase que representa una sección dentro del concesionario.
 * Agrupa vehículos según características específicas como SUVs o utilitarios.
 *
 * Cada sección tiene un identificador único y una descripción.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ID;
    private String descripcion;

    /**
     * Constructor por defecto de la clase Seccion.
     * Inicializa los valores de identificador y descripción como cadenas vacías.
     */
    public Seccion(){
        this.ID = "";
        this.descripcion = "";
    }

    /**
     * Constructor que inicializa una sección con un identificador y una descripción.
     * @param ID Identificador único de la sección.
     * @param descripcion Descripción de la sección.
     */
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

    /**
     * Compara esta sección con otra para determinar si son iguales.
     * Dos secciones se consideran iguales si tienen el mismo identificador.
     * @param obj Objeto a comparar con esta sección.
     * @return {@code true} si los identificadores son iguales, {@code false} en caso contrario.
     */
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
