package modelo;

import java.math.BigDecimal;

public class Tapiceria implements Mejora{
    private String nombre;
    private BigDecimal precio;
    private BigDecimal incremento;

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
