package modelo;

import ES.MyInput;
import menus.Menu;

import java.math.BigDecimal;
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
                case 1: // alta de una venta
                    altaVenta();
                    break;
                case 2: // baja de una venta
                    bajaVenta();
                    break;
                case 3: // modificacion de una venta
                    modVenta();
                    break;
                case 4: // consulta del historial de ventas
                    mostrarVentas();
                    break;
            }
        }
    }

    public void showVenta(Venta v){
        System.out.println("--------------------------------------------------------");
        System.out.println("Vehículo: " + v.getVehiculo().getMarca() + " - " + v.getVehiculo().getModelo());
        System.out.println("Cliente: " + v.getCliente().getNombre() + " " + v.getCliente().getApellidos());
        System.out.println("Matrícula: " + v.getMatricula());
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
        if (venta.getCliente() == null){return;}
        System.out.println("========================================================");
        System.out.println("========================================================");
        System.out.println("Ahora el coche que está comprando");
        venta.setVehiculo( gestionVehiculos.eligeVehiculo(true) );
        if (venta.getVehiculo() == null){return;}
        venta.getVehiculo().updateStock(-1);
        System.out.println("========================================================");
        venta.setMatricula(c.crearMatricula());
        System.out.println("Al coche se le ha asignado la matrícula: " + venta.getMatricula());
        this.c.addVenta(venta);
    }

    public void bajaVenta(){
        System.out.println("Elige la venta a dar de baja");
        Venta venta = eligeVenta();
        c.rmVenta(venta);
        System.out.println("========================================================");
    }

    public void mostrarVentas(){
        if(c.getArraySecciones().isEmpty()) {
            System.out.println("Este concesionario no ha realizado ninguna venta todavía");
        }
        else{
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

    public void modVenta(){
        do{
            System.out.println("Elige la venta a modificar");
            Venta venta = eligeVenta();
            List<String> matriculas = c.getArrayMatriculas();
            // pendiente ver que hacemos con la seccion
            System.out.println("Vehículo actual: " + venta.getVehiculo().getMarca() + " " + venta.getVehiculo().getModelo());
            Vehiculo v = gestionVehiculos.eligeVehiculo(true);
            if (v != null) {
                venta.setVehiculo(v);
            }
            System.out.println("Cliente actual: " + venta.getCliente().getNombre() + " " + venta.getCliente().getApellidos());
            Cliente c = gestionClientes.eligeCliente();
            if (c != null){//que nunca va a ser null por la implementación actual de eligeCliente
                venta.setCliente(c);
            }
            System.out.println("La matrícula no se puede modificar");

            showVenta(venta);
        }while(MyInput.yesNoQuestion("¿Quieres modificar los datos de otra venta?"));
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