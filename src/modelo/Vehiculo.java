package modelo;

import java.io.Serializable;
//import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un vehículo en el concesionario.
 * Contiene información como marca, modelo, año de fabricación, precio base, stock,
 * y las mejoras aplicadas al vehículo.
 * Permite gestionar las propiedades del vehículo y aplicar mejoras específicas.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Seccion seccion;
    private String marca;
    private String modelo;
    private String anioFabric;
    private BigDecimal precioBase;
    private int stock;
    private final ArrayList<Mejora> mejoras;

    /**
     * Constructor por defecto para la clase Vehiculo.
     * Inicializa los atributos con valores predeterminados.
     */
    public Vehiculo(){
        this.seccion = null;
        this.marca = "";
        this.modelo = "";
        this.anioFabric = "";
        this.precioBase = BigDecimal.ZERO;
        this.stock = 0;
        this.mejoras = new ArrayList<>();
    }

    /**
     * Constructor que inicializa un vehículo con parámetros específicos.
     * @param seccion Sección del vehículo.
     * @param marca Marca del vehículo.
     * @param modelo Modelo del vehículo.
     * @param anioFabric Año de fabricación del vehículo.
     * @param precioBase Precio base del vehículo.
     * @param stock Stock inicial del vehículo.
     */
    public Vehiculo(Seccion seccion,
                    String marca,
                    String modelo,
                    String anioFabric,
                    BigDecimal precioBase,
                    int stock,
                    ArrayList<Mejora> mejoras){
        this.seccion = seccion;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabric = anioFabric;
        this.precioBase = precioBase;
        this.stock = stock;
        this.mejoras = mejoras;
    }

    //vehiculo como unidad
    //TODO aquí irán las mejoras
    public Vehiculo(Vehiculo v){
        seccion = v.seccion;
        marca = v.marca;
        modelo = v.marca;
        anioFabric = v.anioFabric;
        precioBase = v.precioBase;
        stock = 1; //para indicar que es una unica unidad
        mejoras = v.mejoras;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnioFabric() {
        return anioFabric;
    }

    public void setAnioFabric(String anioFabric) {
        this.anioFabric = anioFabric;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Actualiza el stock del vehículo sumando o restando una cantidad específica.
     * @param qtty Cantidad a ajustar (puede ser negativa para restar).
     */
    public void updateStock(int qtty){
        this.stock += qtty;
    }

    public List<Mejora> getMejoras(){
        return mejoras;
    }

    public void addMejora(Mejora mejora) {
        if (!mejoras.contains(mejora)) { // Verificar que la mejora no esté duplicada
            mejoras.add(mejora);
            System.out.println("Mejora añadida: " + mejora);
        } else {
            System.out.println("La mejora \"" + mejora + "\" ya está aplicada a este vehículo.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Vehiculo v2 = (Vehiculo) obj;
        if (this.modelo == null){
            return v2.modelo == null;
        }
        return this.modelo.equals(v2.modelo);
    }

}
