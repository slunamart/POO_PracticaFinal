package modelo;

import ES.MyInput;
import menus.Menu;

import java.util.ArrayList;
import java.util.List;

public class GestionVentas {

    private final Concesionario c;
    private GestionClientes gestionClientes;
    private GestionVehiculos gestionVehiculos;


    private GestionSecciones gestionSecciones;

    public GestionVentas(Concesionario c){
        this.c = c;
    }

    public void showMenu(){

        if( this.c.sizeCliente() == 0 ){
            System.out.println("No hay clientes en el concesionario");
            if ( MyInput.yesNoQuestion("¿quieres añadir uno?") ) {
                this.gestionClientes.altaCliente();
            }
        }

        if( this.c.sizeSeccion() == 0){
            System.out.println("No hay Secciones en el concesionario");
            if (MyInput.yesNoQuestion("¿quieres añadir una?") ) {
                this.gestionSecciones.altaSeccion();
            }
        }
        if (this.c.sizeSeccion() != 0 && this.c.sizeVehiculo() == 0){
            System.out.println("No hay vehículos en el concesionario");
            if (MyInput.yesNoQuestion("¿quieres añadir uno?")) {
                this.gestionVehiculos.altaVehiculo();
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
                case 1: // alta de una seccion
                    altaVenta();
                    break;
                case 2: // baja de una sección
                    bajaVenta();
                    break;
                case 3: // modificacion de una seccion

                    break;
                case 4: // consulta de secciones disponibles
                    mostrarVentas();
                    break;
            }
        }
    }

    public Venta eligeVenta(){
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
            return eligeVenta();
        }
        else{
            return c.getArrayVentas().get(opcion - 1);
        }

    }

    public void altaVenta(){
        Venta venta = new Venta();
        System.out.println("========================================================");
        System.out.println("Elije el cliente que está haciendo la compra");
        venta.setCliente( gestionClientes.eligeCliente() );
        System.out.println("========================================================");
        System.out.println("========================================================");
        System.out.println("Ahora el coche que está comprando");
        venta.setVehiculo( gestionVehiculos.eligeVehiculo() );
        System.out.println("========================================================");
        venta.setMatricula(c.crearMatricula());
        System.out.println("Al coche se le ha asignado la matrícula: " + venta.getMatricula());
        this.c.addVenta(venta);
    }

    public void bajaVenta(){
        System.out.println("bajaVenta");
    }

    public void mostrarVentas(){
        if(c.getArraySecciones().isEmpty()) {
            System.out.println("Este concesionario no ha realizado ninguna venta todavía");
        }
        else{
            System.out.println("=======================================================");
            System.out.println("-- NO mostraremos las matrículas por razones de privacidad"); //no porque no sepamos hacerlo
            for (Venta v2 : this.c.getArrayVentas()) {
                System.out.println("---------------------------------------------------");
                System.out.println("Nombre completo: " + v2.getCliente().getNombre() + " " + v2.getCliente().getApellidos());
                System.out.println("Coche que adquirió: " + v2.getVehiculo().getMarca() + " " + v2.getVehiculo().getModelo());
            }
        }
        System.out.println("=======================================================");
        MyInput.waitForIntro();
    }

    public void setGestionClientes(GestionClientes gestionClientes) {
        this.gestionClientes = gestionClientes;
    }

    public void setGestionVehiculos(GestionVehiculos gestionVehiculos) {
        this.gestionVehiculos = gestionVehiculos;
    }

    public void setGestionSecciones(GestionSecciones gestionSecciones) {
        this.gestionSecciones = gestionSecciones;
    }
}