package principal;

import ES.MyInput;
import menus.Menu;
import modelo.Concesionario;
import modelo.GestionSecciones;
import modelo.GestionVehiculos;
import modelo.Vehiculo;

import java.math.BigDecimal;

public class AppConcesionario {



    public static void main(String[] args) {
        Concesionario c = new Concesionario();

        Menu principal = new Menu( "Menú Principal",
                            new String[]{ "Secciones",
                                          "Mantenimiento del parque de vehículos",
                                          "Mantenimiento de clientes" } );

        int opcion = -1;
        GestionSecciones gs = new GestionSecciones(c);
        GestionVehiculos gv = new GestionVehiculos(c);
        gv.setSecciones( gs );
        while( opcion != 0 ){
            opcion = principal.show();
            switch( opcion ) {
                case 1:
                    gs.showMenu();
                    break;
                case 2:
                    gv.showMenu();
                    break;
                case 3:
                    System.out.println("Aquí irá el mantenimiento de clientes");
                    break;
            }
        }
        System.out.println("!!!!!Gracias por usar nuestro programa!!!!!");
    }

}
