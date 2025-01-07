package modelo;

import java.math.BigDecimal;

public  class Llantas implements Mejora{
    private String nombre;
    private BigDecimal precio;
    private BigDecimal incremento;

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

    @Override
    public void aplicar(){
        System.out.println("Aplicando mejora *" + nombre + "*. Precio: " + precio + "€.");
    }
}
