package modelo;

import menus.Menu;

import java.util.ArrayList;
import java.util.List;

public class GestionVentas {

    private final Concesionario c;

    public GestionVentas(Concesionario c){
        this.c = c;
    }

    public void showMenu(){
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
        //System.out.println("Elige el cliente que está haciendo la compra");
    }

    public void bajaVenta(){
        System.out.println("bajaVenta");
    }

    public void mostrarVentas(){
        System.out.println("mostrarVentas");
    }

}
