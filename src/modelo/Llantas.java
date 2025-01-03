package modelo;

public abstract class Llantas implements Mejora{
    private String nombre;
    private double precio;

    public Llantas(double precio){
        this.nombre = "Llantas de aleación";
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
