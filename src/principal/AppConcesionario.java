package principal;

import ES.MyInput;
import menus.Menu;
import modelo.Concesionario;
import modelo.Vehiculo;

import java.math.BigDecimal;

public class AppConcesionario {

    private static Vehiculo altaVehiculo(){
        System.out.println("Alta de un vehículo");
        Vehiculo vehiculo = new Vehiculo();
        // MyInput.readString("Seccion");
        vehiculo.setModelo( MyInput.readString("Modelo") );
        vehiculo.setAnioFabric( Integer.toString( MyInput.readInt("Año de fabricacion") ) ) ;
        vehiculo.setPrecioBase( BigDecimal.valueOf( MyInput.readDouble("precioBase") ) );
        vehiculo.setStock( MyInput.readInt("Stock") );
        return vehiculo;
    }


    public static void main(String[] args) {
        Concesionario c = new Concesionario();

        Menu principal = new Menu("Menú principal",
                                    new String[]{"Alta de coches",
                                                "Baja coches",
                                                "Consultar coches",
                                                "Aumentar stock",
                                                "Mostrar todos los coches en una sección"});
        int opcion;
        do{
            opcion = principal.show();
            switch (opcion){
                case 1:
                    Vehiculo v = altaVehiculo();
                    c.getVehiculos().add( v );
                    break;
                case 2: System.out.println("Baja de coches"); break;
                case 3: System.out.println("Consultar coches"); break;
                case 4: System.out.println("Aumentar stock"); break;
                case 5: System.out.println("Mostrar todos los coches en una sección"); break;
                default:
                    // no hacer nada
            }
        } while( opcion != 0 );


    }


}
