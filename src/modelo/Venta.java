package modelo;

import java.io.Serializable;

public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private String matricula;

    public Venta(){
        this.vehiculo = null;
        this.cliente = null; //cambiar por el nobody
    }

    public Venta(Vehiculo v, Cliente cl ){
        this.vehiculo = v;
        this.cliente = cl;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }



}