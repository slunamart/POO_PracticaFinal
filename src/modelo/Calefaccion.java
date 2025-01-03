package modelo;

public abstract class Calefaccion implements Mejora{
    private String nombre;
    private double precio;

    public Calefaccion(double precio){
        this.nombre = "Calefacción";
        this.precio = precio;
    }

    @Override
    public String getNombre(){
        return nombre;
    }

    @Override
    public double getPrecio(){
        return precio;
    }
}
