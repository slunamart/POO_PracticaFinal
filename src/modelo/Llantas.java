package modelo;

import java.math.BigDecimal;

/**
 * Clase que representa una mejora de llantas de aleación para un vehículo.
 * Implementa la interfaz {@code Mejora}, añadiendo atributos como el precio y el incremento.
 *
 * Mejora la estética y el rendimiento del vehículo.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public  class Llantas implements Mejora{
    private String nombre;
    private BigDecimal precio;
    private BigDecimal incremento;

    /**
     * Constructor de la clase Llantas.
     * Calcula el precio basado en el coste base e incluye el incremento correspondiente.
     * @param coste Coste base sobre el que se calculará el precio con el incremento.
     */
    public Llantas(BigDecimal coste){
        this.nombre = "Llantas de aleación";
        this.incremento = new BigDecimal(1.05);
        this.precio = coste.multiply(incremento);
    }

    @Override
    public String getNombre(){
        return nombre;
    }

    @Override
    public BigDecimal getIncremento(){
        return incremento;
    }

    @Override
    public BigDecimal getPrecio(){
        return precio;
    }

    /**
     * Aplica la mejora al vehículo.
     * Este método imprime un mensaje indicando que la mejora ha sido aplicada.
     */
    @Override
    public void aplicar(){
        System.out.println("Aplicando mejora *" + nombre + "*. Precio: " + precio + "€.");
    }
}
