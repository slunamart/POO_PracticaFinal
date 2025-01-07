package modelo;

import java.math.BigDecimal;

/**
 * Clase que representa una mejora de tapicería de cuero para un vehículo.
 * Implementa la interfaz {@code Mejora}, proporcionando atributos como
 * el precio y el incremento asociado con esta mejora.
 * Mejora la estética y la comodidad del interior del vehículo.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class Tapiceria implements Mejora{
    private final String nombre;
    private final BigDecimal precio;
    private final BigDecimal incremento;

    /**
     * Constructor de la clase Tapiceria.
     * Calcula el precio basado en el coste base e incluye el incremento correspondiente.
     * @param coste Coste base sobre el que se calculará el precio con el incremento.
     */
    public Tapiceria(BigDecimal coste){ //coste es el precio del vehículo, que se multiplica por el incremento de la mejora, y resulta en el precio final de la mejora.
        this.nombre = "Tapicería de cuero";
        this.incremento = new BigDecimal(1.1);
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

    @Override
    public void aplicar(){
        System.out.println("Aplicando mejora *" + nombre + "*. Precio: " + incremento + "€.");
    }
}
