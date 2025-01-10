package modelo;

import java.io.Serializable;

/**
 * Clase que representa una venta realizada en el concesionario.
 * Incluye información sobre el cliente, el vehículo vendido y la matrícula asignada.
 * Permite gestionar la asociación de un cliente con un vehículo durante una transacción específica.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private String matricula;
    private String ID;

    /**
     * Constructor por defecto para la clase Venta.
     * Inicializa los atributos con valores predeterminados.
     */
    public Venta(){
        this.vehiculo = null;
        this.cliente = null; //cambiar por el nobody
        this.ID = "";
    }

    /**
     * Constructor que inicializa una venta con un cliente y un vehículo.
     * @param v Vehículo asociado a la venta.
     * @param cl Cliente asociado a la venta.
     */
    public Venta(Vehiculo v, Cliente cl, String ID){
        this.vehiculo = v;
        this.cliente = cl;
        this.ID = ID;
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

    public void setID(int cont) {
        this.ID = String.format("V%09d", cont);
    }

    public String getID(){
        return ID;
    }


}