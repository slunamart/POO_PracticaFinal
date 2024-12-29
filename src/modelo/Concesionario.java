package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Concesionario implements Serializable {

    private List<Seccion> secciones;
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<Venta> ventas;


    public Concesionario (){
        this.secciones = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public void addSeccion( Seccion s ){
        this.secciones.add( s );
    }

    public int sizeSeccion(){
        return this.secciones.size();
    }

    public void addVehiculo(Vehiculo v){
        this.vehiculos.add(v);
    }

    public Vehiculo getVehiculo(Vehiculo v){
        for ( Vehiculo v2 : vehiculos){
            if (v2.equals(v)){
                return v2;
            }
        }
        return null;
    }
    public boolean existeVehiculo(Vehiculo v){
        for ( Vehiculo v2 : vehiculos){
            if (v2.equals(v)){
                return true;
            }
        }
        return false;
    }

    public void rmVehiculo(Vehiculo v, int qtty){
        for ( Vehiculo v2 : vehiculos){
            if (v2.equals(v)){
                v2.updateStock(qtty);
            }
        }
    }

    public int sizeVehiculo(){
        return this.vehiculos.size();
    }

    public List<Vehiculo> getArrayVehiculos(){
        return vehiculos;
    }
}
