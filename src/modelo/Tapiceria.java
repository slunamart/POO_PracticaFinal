package modelo;

public abstract class Tapiceria implements Mejora{
    private String nombre;
    private double precio;

    public Tapiceria(double precio){
        this.nombre = "Tapicería de cuero";
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
