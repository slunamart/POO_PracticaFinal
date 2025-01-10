package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un concesionario.
 * Gestiona clientes, vehículos, secciones, ventas y matrículas.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */ 
public class Concesionario implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Seccion noSeccion;
    private final GenericGestionable<Seccion> secciones;
    private final GenericGestionable<Cliente> clientes;
    private final GenericGestionable<Vehiculo> vehiculos;
    private final List<Venta> ventas;
    private final List<String> matriculas;


    /**
     * Constructor que inicializa el concesionario con listas vacías.
     */
    public Concesionario (){
        noSeccion = new Seccion( "Sin Sección", "No añadido a ninguna sección todavía" );
        this.secciones = new GenericGestionable<>();
        this.clientes = new GenericGestionable<>();
        this.vehiculos = new GenericGestionable<>();
        this.ventas = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    /**
     * Obtiene la sección predeterminada.
     * @return Sección predeterminada sin categoría específica.
     */
    public Seccion getSinSeccion(){
        return this.noSeccion;
    }

    /**
     * Agrega una nueva sección al concesionario.
     * @param s Sección a agregar.
     */
    public void addSeccion( Seccion s ){
        this.secciones.alta( s );
    }

    /**
     * Obtiene la cantidad de secciones en el concesionario.
     * @return Número de secciones.
     */
    public int sizeSeccion(){
        return this.secciones.size();
    }

    /**
     * Agrega un vehículo al concesionario.
     * @param v Vehículo a agregar.
     */
    public void addVehiculo(Vehiculo v){
        this.vehiculos.alta(v);
    }

    /**
     * Busca un vehículo específico.
     * @param s Vehículo a buscar.
     * @return El vehículo encontrado o {@code null} si no existe.
     */
    public Seccion getSeccion(Seccion s){
        // como nunca habrá un elemento repetido me quedo siempre con el primero
        return secciones.buscar(v2 -> v2.equals(s)).get(0);
    }

    /**
     * Busca un vehículo específico.
     * @param v Vehículo a buscar.
     * @return El vehículo encontrado o {@code null} si no existe.
     */
    public Vehiculo getVehiculo(Vehiculo v) {
        // como nunca habrá un elemento repetido me quedo siempre con el primero
        return vehiculos.buscar(v2 -> v2.equals(v)).get(0);
    }

    /**
     * Verifica si un vehículo existe en el concesionario.
     * @param v Vehículo a verificar.
     * @return {@code true} si el vehículo existe, {@code false} en caso contrario.
     */
    public boolean existeVehiculo(Vehiculo v) {
        return vehiculos.contiene(v2 -> v2.equals(v));
    }

    /**
     * Reduce el stock de un vehículo específico.
     * @param v Vehículo al que se le reducirá el stock.
     */
    public void rmVehiculo(Vehiculo v){
        vehiculos.baja(v);
    }

    /**
     * Elimina una sección específica del concesionario.
     * @param s Sección a eliminar.
     */
    public void rmSeccion(Seccion s){
        secciones.baja(s);
    }

    /**
     * Obtiene la cantidad de vehículos disponibles.
     * @return Número de vehículos.
     */
    public int sizeVehiculo(){
        return this.vehiculos.size();
    }

    public int sizeVenta(){
        return this.ventas.size();
    }

    /**
     * Obtiene la lista de vehículos disponibles.
     * @return Lista de vehículos.
     */
    public List<Vehiculo> getArrayVehiculos(){
        return vehiculos.getArray();
    }

    /**
     * Obtiene la lista de secciones disponibles.
     * @return Lista de secciones.
     */
    public List<Seccion> getArraySecciones(){
        return secciones.getArray();
    }

    /**
     * Obtiene la lista de matrículas.
     * @return Lista de matrículas.
     */
    public List<String> getArrayMatriculas(){
        return matriculas;
    }

    /**
     * Obtiene la lista de ventas realizadas.
     * @return Lista de ventas.
     */
    public List<Venta> getArrayVentas(){
        return ventas;
    }

    public void rmVenta(Venta v){
        ventas.remove(v);
    }

    public boolean existeSeccion(Seccion s){
        return secciones.contiene(s2 -> s2.equals(s));
    }

    //parte de matrículas
    public boolean existeMatricula(String m){
        for (String m2 : matriculas){
            if (m2.equals(m)){
                return true;
            }
        }
        return false;
    }

    /**
     * Crea y agrega una nueva matrícula al sistema.
     * @return Nueva matrícula generada.
     */
    public String crearMatricula() {
        String nuevaMatricula;
        List<String> matriculasExistentes = getArrayMatriculas();

        if (matriculasExistentes.isEmpty()) {
            nuevaMatricula = "0000AAA"; // Primera matrícula si la lista está vacía
        } else {
            String ultimaMatricula = matriculasExistentes.get(matriculasExistentes.size() - 1);
            nuevaMatricula = incrementarMatricula(ultimaMatricula);
        }

        matriculas.add(nuevaMatricula);
        return nuevaMatricula;
    }

    //Parte auxiliar para incrementar una matrícula en orden
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

    /**
     * Busca un cliente por su DNI.
     * @param dni DNI del cliente.
     * @return Cliente encontrado o {@code null} si no existe.
     */
    public Cliente buscarPorDNI(String dni) {
        for (Cliente cliente : clientes.getArray()) {
            if (cliente.getDNI().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Agrega un nuevo cliente al concesionario.
     * @param cl Cliente a agregar.
     */
    public void addCliente(Cliente cl){
        clientes.alta(cl);
    }

    /**
     * Elimina un cliente del concesionario.
     * @param cl Cliente a eliminar.
     */
    public void rmCliente(Cliente cl){
        clientes.baja(cl);
    }

    /**
     * Agrega una venta al historial de ventas.
     * @param v Venta a agregar.
     */
    public void addVenta(Venta v){
        ventas.add(v);
    }

    /**
     * Obtiene la cantidad de clientes registrados.
     * @return Número de clientes.
     */
    public int sizeCliente(){
        return clientes.size();
    }

    /**
     * Obtiene la lista de clientes.
     * @return Lista de clientes.
     */
    public List<Cliente> getArrayClientes(){
        return clientes.getArray();
    }

}