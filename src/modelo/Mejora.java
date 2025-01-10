package modelo;

import java.math.BigDecimal;

/**
 * Interfaz que define el comportamiento de una mejora para un vehículo.
 * Las clases que implementan esta interfaz deben proporcionar detalles
 * como el nombre, el incremento de precio y el costo total de la mejora.
 * Ejemplos de mejoras incluyen GPS, Llantas de aleación y Tapicería.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public interface Mejora {
    /**
     * Obtiene el nombre de la mejora.
     * @return Nombre de la mejora.
     */
    String getNombre();

    /**
     * Obtiene el incremento de precio asociado con la mejora.
     * @return Incremento en porcentaje como un {@code BigDecimal}.
     */
    BigDecimal getIncremento();

    /**
     * Obtiene el precio total de la mejora, calculado con el incremento aplicado.
     * @return Precio total como un {@code BigDecimal}.
     */
    BigDecimal getPrecio();

    /**
     * Aplica la mejora al vehículo.
     * Este método debe incluir la lógica para reflejar los cambios realizados.
     */
    void aplicar();
}