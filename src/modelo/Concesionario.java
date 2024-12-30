package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Concesionario implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Seccion noSeccion;
    private List<Seccion> secciones;
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<Venta> ventas;


    public Concesionario (){
        noSeccion = new Seccion( "Sin Sección", "" );
        this.secciones = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public Seccion getSinSeccion(){
        return this.noSeccion;
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

    public void rmSeccion(Seccion s){
        secciones.remove(s);
    }

    public int sizeVehiculo(){
        return this.vehiculos.size();
    }

    public List<Vehiculo> getArrayVehiculos(){
        return vehiculos;
    }

    public List<Seccion> getArraySecciones(){
        return secciones;
    }

    public boolean existeSeccion(Seccion s){
        for (Seccion s2 : secciones){
            if (s2.equals(s)){
                return true;
            }
        }
        return false;
    }

}
