package modelo;

import ES.MyInput;
import menus.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar el módulo de ventas en un concesionario.
 * Permite realizar altas, bajas, modificaciones y consultas relacionadas con ventas.
 * Gestiona las interacciones entre clientes, vehículos y el concesionario en general.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class GestionVentas implements Gestionable{

    private final Concesionario c;
    private GestionClientes gestionClientes;
    private GestionVehiculos gestionVehiculos;


    private GestionSecciones gestionSecciones;

    /**
     * Constructor de la clase GestionVentas.
     * @param c Concesionario asociado a las ventas.
     */
    public GestionVentas(Concesionario c){
        this.c = c;
    }

    /**
     * Muestra el menú de opciones relacionadas con ventas.
     * Permite realizar operaciones como vender, modificar o dar de baja ventas.
     */
    @Override
    public void showMenu(){

        if( this.c.sizeCliente() == 0 ){
            System.out.println("No hay clientes en el concesionario");
            if ( MyInput.yesNoQuestion("¿quieres añadir uno?") ) {
                this.gestionClientes.alta();
            }
        }

        if( this.c.sizeSeccion() == 0){
            System.out.println("No hay Secciones en el concesionario");
            if (MyInput.yesNoQuestion("¿quieres añadir una?") ) {
                this.gestionSecciones.alta();
            }
        }
        if (this.c.sizeSeccion() != 0 && this.c.sizeVehiculo() == 0){
            System.out.println("No hay vehículos en el concesionario");
            if (MyInput.yesNoQuestion("¿quieres añadir uno?")) {
                this.gestionVehiculos.alta();
            }
        }

        // al llegar a este punto, pueden no cumplirse los requisitos aún,
        // por lo que no queda más remedio que verificarlo de nuevo
        if( this.c.sizeCliente() == 0 && this.c.sizeVehiculo() == 0 && this.c.sizeSeccion() == 0 ) {
            System.out.println("No hay ni clientes, ni vehículos, ni secciones disponibles");
            return;
        }

        Menu ventas = new Menu( "Menú Ventas",
                new String[]{ "Vender un coche",
                        "Deshacer una venta",
                        "Modificación de una venta",
                        "Historial de ventas"} );

        int opcion = -1;
        while( opcion != 0 ){
            opcion = ventas.show();
            switch( opcion ){
                case 1: // alta de una venta
                    alta();
                    break;
                case 2: // baja de una venta
                    baja();
                    break;
                case 3: // modificacion de una venta
                    modificacion();
                    break;
                case 4: // consulta del historial de ventas
                    mostrarVentas();
                    break;
            }
        }
    }

    /**
     * Muestra la información detallada de una venta específica.
     * @param v Venta de la que se mostrará la información.
     */
    public void showVenta(Venta v){
        System.out.println("--------------------------------------------------------");
        System.out.println("Vehículo: " + v.getVehiculo().getMarca() + " - " + v.getVehiculo().getModelo());
        System.out.println("Cliente: " + v.getCliente().getNombre() + " " + v.getCliente().getApellidos());
        System.out.println("Matrícula: " + v.getMatricula());
    }

    /**
     * Permite al usuario seleccionar una venta de la lista de ventas disponibles.
     * @return La venta seleccionada.
     */
    public Venta elige(){
        List<String> historial = new ArrayList<>();
        for (Venta v2 : c.getArrayVentas()){
            historial.add( v2.getCliente().getNombre() + " " +
                    v2.getCliente().getApellidos() + " -> " +
                    v2.getVehiculo().getMarca() + " " +
                    v2.getVehiculo().getModelo() + " : " +
                    v2.getMatricula() );
        }
        Menu menu_ventas = new Menu("Menú ventas",
                historial.toArray(new String[0] ) );
        int opcion = menu_ventas.show();
        if (opcion == 0){
            System.out.println("Venta no encontrada");
            return null;
        }
        else{
            return c.getArrayVentas().get(opcion - 1);
        }

    }

    /**
     * Crea una nueva venta en el sistema y la agrega al concesionario.
     */
    @Override
    public void alta(){
        Venta venta = new Venta();
        System.out.println("========================================================");
        System.out.println("Elige el cliente que está haciendo la compra");
        venta.setCliente( gestionClientes.elige() );
        if (venta.getCliente() == null){return;}
        System.out.println("========================================================");
        System.out.println("========================================================");

        String IDbuscar = MyInput.readString("Introduzca el ID del coche que está comprando");
        venta.setVehiculo( c.buscarPorID(IDbuscar) );
        Vehiculo v = venta.getVehiculo();
        if (venta.getVehiculo() == null){return;}
        venta.getVehiculo().updateStock(-1);

        // Menú de implementación de mejoras:
        System.out.print("========================================================");
        Menu menu_mejoras = new Menu("Menú de mejoras:",
                new String[]{"Añadir tapicería de cuero.",
                        "Añadir sistema GPS.",
                        "Añadir llantas de aleación.",
                        "Añadir calefacción"});
        int opcion;

        do{
            opcion = menu_mejoras.show();
            switch(opcion){
                case 1:
                    v.addMejora(new Tapiceria(v.getPrecioBase()));
                    break;
                case 2:
                    v.addMejora((new GPS(v.getPrecioBase())));
                    break;
                case 3:
                    v.addMejora(new Llantas(v.getPrecioBase()));
                    break;
                case 4:
                    v.addMejora(new Calefaccion(v.getPrecioBase()));
                    break;
                default:

            }
        }while(opcion != 0);

        if(v.getStock() < 1){
            System.out.println("ERROR: No se puede vender un vehículo sin stock.");
            return;
        }
        System.out.println("========================================================");
        venta.setMatricula(c.crearMatricula());
        System.out.println("Al coche se le ha asignado la matrícula: " + venta.getMatricula());
        venta.setID(c.sizeVenta()+1);
        this.c.addVenta(venta);
    }

    /**
     * Elimina una venta existente del sistema.
     */
    @Override
    public void baja(){
        System.out.println("Elige la venta a dar de baja");
        Venta venta = elige();
        c.rmVenta(venta);
        Vehiculo v = venta.getVehiculo();
        v.setStock(v.getStock()+1);
        System.out.println("========================================================");
    }

    /**
     * Muestra el historial de ventas realizadas en el concesionario.
     */
    public void mostrarVentas(){
        if(c.getArraySecciones().isEmpty()) {
            System.out.println("Este concesionario no ha realizado ninguna venta todavía");
        }
        else{
            System.out.println("-- NO mostraremos las matrículas por razones de privacidad"); //no porque no sepamos hacerlo
            for (Venta v2 : this.c.getArrayVentas()) {
                System.out.println("---------------------------------------------------");
                System.out.println("ID: " + v2.getID());
                System.out.println("Nombre completo: " + v2.getCliente().getNombre() + " " + v2.getCliente().getApellidos());
                System.out.println("Coche que adquirió: " + v2.getVehiculo().getMarca() + " " + v2.getVehiculo().getModelo());
            }
        }
        System.out.println("=======================================================");
        MyInput.waitForIntro();
    }

    //TODO ver que pasa con el parámetro sin usar
    // comprobar que al asignar una matrícula comprueba que no se repite
    /**
     * Permite modificar los datos de una venta existente.
     */
    @Override
    public void modificacion(){
        do{
            System.out.println("Elige la venta a modificar");
            Venta venta = elige();
            List<String> matriculas = c.getArrayMatriculas();
            // pendiente ver que hacemos con la seccion
            System.out.println("Vehículo actual: " + venta.getVehiculo().getMarca() + " " + venta.getVehiculo().getModelo());
            Vehiculo v = gestionVehiculos.elige(true);
            if (v != null) {
                venta.setVehiculo(v);
            }
            System.out.println("Cliente actual: " + venta.getCliente().getNombre() + " " + venta.getCliente().getApellidos());
            Cliente c = gestionClientes.elige();
            if (c != null){//que nunca va a ser null por la implementación actual de eligeCliente
                venta.setCliente(c);
            }
            System.out.println("La matrícula no se puede modificar");

            showVenta(venta);
        }while(MyInput.yesNoQuestion("¿Quieres modificar los datos de otra venta?"));
    }

    /**
     * Configura el módulo de gestión de clientes para este sistema de ventas.
     * @param gestionClientes Gestión de clientes asociada.
     */
    public void setGestionClientes(GestionClientes gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    /**
     * Configura el módulo de gestión de vehículos para este sistema de ventas.
     * @param gestionVehiculos Gestión de vehículos asociada.
     */
    public void setGestionVehiculos(GestionVehiculos gestionVehiculos) {
        this.gestionVehiculos = gestionVehiculos;
    }

    /**
     * Configura el módulo de gestión de secciones para este sistema de ventas.
     * @param gestionSecciones Gestión de secciones asociada.
     */
    public void setGestionSecciones(GestionSecciones gestionSecciones) {
        this.gestionSecciones = gestionSecciones;
    }
}