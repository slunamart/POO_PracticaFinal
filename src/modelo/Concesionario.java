package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Concesionario implements Serializable {

    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<Venta> ventas;


    public Concesionario (){
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public void addVehiculo(Vehiculo v){
        this.vehiculos.add(v);
    }

    public int sizeVehiculo(){
        return this.vehiculos.size();
    }

}
