package modelo;

import java.math.BigDecimal;

public interface Mejora {
    String getNombre();
    BigDecimal getIncremento();
    BigDecimal getPrecio();
    void aplicar();
}
