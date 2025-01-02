package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Concesionario implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Seccion noSeccion;
    private List<Seccion> secciones;
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<Venta> ventas;
    private List<String> matriculas;


    public Concesionario (){
        noSeccion = new Seccion( "Sin Sección", "" );
        this.secciones = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    public Seccion getSinSeccion(){
        return this.noSeccion;
    }

    public void addSeccion( Seccion s ){
        this.secciones.add( s );
    }

    public int sizeSeccion(){
        return this.secciones.size();
    }

    public void addVehiculo(Vehiculo v){
        this.vehiculos.add(v);
    }

    public Vehiculo getVehiculo(Vehiculo v){
        for ( Vehiculo v2 : vehiculos){
            if (v2.equals(v)){
                return v2;
            }
        }
        return null;
    }

    public boolean existeVehiculo(Vehiculo v){
        for ( Vehiculo v2 : vehiculos){
            if (v2.equals(v)){
                return true;
            }
        }
        return false;
    }

    public void rmVehiculo(Vehiculo v, int qtty){
        for ( Vehiculo v2 : vehiculos){
            if (v2.equals(v)){
                v2.updateStock(qtty);
            }
        }
    }

    public void rmSeccion(Seccion s){
        secciones.remove(s);
    }

    public int sizeVehiculo(){
        return this.vehiculos.size();
    }

    public List<Vehiculo> getArrayVehiculos(){
        return vehiculos;
    }

    public List<Seccion> getArraySecciones(){
        return secciones;
    }

    public List<String> getArrayMatriculas(){
        return matriculas;
    }

    public List<Venta> getArrayVentas(){
        return ventas;
    }

    public boolean existeSeccion(Seccion s){
        for (Seccion s2 : secciones){
            if (s2.equals(s)){
                return true;
            }
        }
        return false;
    }

    //parte de matrículas
    public String crearMatricula() {
        String nuevaMatricula;
        List<String> matriculasExistentes = getArrayMatriculas();

        if (matriculasExistentes.isEmpty()) {
            nuevaMatricula = "0000AAA"; // Primera matrícula si la lista está vacía
        } else {
            String ultimaMatricula = matriculasExistentes.get(matriculasExistentes.size() - 1);
            nuevaMatricula = incrementarMatricula(ultimaMatricula);
        }

        return nuevaMatricula;
    }

    // Método auxiliar para incrementar una matrícula en orden
    private String incrementarMatricula(String matricula) {
        String numeros = matricula.substring(0, 4);
        String letras = matricula.substring(4);

        // Incrementar números
        int num = Integer.parseInt(numeros);
        num++;

        if (num > 9999) {
            num = 0;
            letras = incrementarLetras(letras);
        }

        return String.format("%04d%s", num, letras);
    }

    // Método auxiliar para incrementar las letras
    private String incrementarLetras(String letras) {
        char[] letrasArray = letras.toCharArray();

        for (int i = letrasArray.length - 1; i >= 0; i--) {
            if (letrasArray[i] < 'Z') {
                letrasArray[i]++;
                break;
            } else {
                letrasArray[i] = 'A';
            }
        }

        return new String(letrasArray);
    }

}
