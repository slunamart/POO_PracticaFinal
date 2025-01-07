package modelo;

import java.math.BigDecimal;

public class GPS implements Mejora{
    private String nombre;
    private BigDecimal precio;
    private BigDecimal incremento;

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

    @Override
    public void aplicar(){
        System.out.println("Aplicando mejora *" + nombre + "*. Precio: " + precio + "€.");
    }
}
