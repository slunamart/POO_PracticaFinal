package modelo;

import java.math.BigDecimal;

/**
 * Clase que representa una mejora de sistema GPS para un vehículo.
 * Implementa la interfaz {@code Mejora}, añadiendo funcionalidad y atributos específicos.
 * Mejora la experiencia de navegación en los vehículos.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class GPS implements Mejora{
    private final String nombre;
    private final BigDecimal precio;
    private final BigDecimal incremento;

    /**
     * Constructor de la clase GPS.
     * Calcula el precio basado en el coste base e incluye el incremento correspondiente.
     * @param coste Coste base sobre el que se calculará el precio con el incremento.
     */
    public GPS(BigDecimal coste){
        this.nombre = "GPS";
        this.incremento = new BigDecimal(1.01);
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