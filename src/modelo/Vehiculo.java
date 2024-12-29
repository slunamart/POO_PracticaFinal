package modelo;

import java.math.BigDecimal;

public class Vehiculo {

    private Seccion seccion;
    private String modelo;
    private String anioFabric;
    private BigDecimal precioBase;
    private int stock;

    public Vehiculo(){
        this.seccion = null;
        this.modelo = "";
        this.anioFabric = "";
        this.precioBase = BigDecimal.ZERO;
        this.stock = 0;
    }

    public Vehiculo(Seccion seccion, String modelo, String anioFabric, BigDecimal precioBase, int stock){
        this.seccion = seccion;
        this.modelo = modelo;
        this.anioFabric = anioFabric;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
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

    public void updateStock(){
        this.stock += 1;
    }
    public void updateStock(int qtty){
        this.stock += qtty;
    }



}
