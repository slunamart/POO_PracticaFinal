package modelo;

import java.io.Serializable;
import java.util.List;

public class Concesionario implements Serializable {

    private Gestionador clientes;
    private Gestionador vehiculos;
    private Gestionador ventas;


    public Concesionario (){
        this.clientes = new Gestionador();
        this.vehiculos = new Gestionador();
        this.ventas = new Gestionador();
    }

    public Gestionador getVehiculos(){
        return this.vehiculos;
    }

}
