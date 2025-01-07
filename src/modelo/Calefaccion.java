package modelo;

import java.math.BigDecimal;

/**
 * Clase que representa una mejora de calefacción para un vehículo.
 * Implementa la interfaz {@code Mejora} y proporciona detalles como precio e incremento asociado.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class Calefaccion implements Mejora{
    private final String nombre;
    private final BigDecimal precio;
    private final BigDecimal incremento;


    /**
     * Constructor de la clase Calefaccion.
     * Calcula el precio basado en el coste base e incluye el incremento correspondiente.
     * @param coste Coste base sobre el que se calculará el precio con el incremento.
     */
    public Calefaccion(BigDecimal coste){
        this.nombre = "Calefacción";
        this.incremento = new BigDecimal(1.05);
        this.precio = coste.multiply(incremento);
    }

    /**
     * Obtiene el nombre de la mejora.
     * @return Nombre de la mejora.
     */
    @Override
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene el incremento aplicado a la mejora.
     * @return Incremento en porcentaje.
     */
    @Override
    public BigDecimal getIncremento(){
        return incremento;
    }

    /**
     * Obtiene el precio total de la mejora tras aplicar el incremento.
     * @return Precio total de la mejora.
     */
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
        System.out.println("Aplicando mejora *" + nombre + "*. Precio: " + incremento + "€.");
    }
}
