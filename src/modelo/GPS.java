package modelo;

public abstract class GPS implements Mejora{
    private String nombre;
    private double precio;

    public GPS(double precio){
        this.nombre = "GPS";
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
