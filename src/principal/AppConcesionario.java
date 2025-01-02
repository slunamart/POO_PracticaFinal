package principal;

import ES.MyInput;
import menus.Menu;
import modelo.Concesionario;
import modelo.GestionSecciones;
import modelo.GestionVehiculos;
import modelo.GestionVentas;

import java.io.File;

public class AppConcesionario {

    static final String nombreFicheroDefecto = "concesionario_produccion.txt";

    public static Concesionario deserializar( String nombreFichero ){
        File f = new File(nombreFichero);
        if(f.exists()) {
            return MyInput.deserialize(nombreFichero);
        }else{
            return new Concesionario();
        }
    }

    public static void serializar( Concesionario c , String nombreFichero ){
        MyInput.serialize(c, nombreFichero);
    }

    public static void main(String[] args) {

        String nombreFichero = nombreFicheroDefecto;
        if( args.length > 0 ){
            nombreFichero = args[0];
        }
        Concesionario c = deserializar(nombreFichero);

        Menu principal = new Menu( "Menú Principal",
                            new String[]{ "Secciones",
                                          "Mantenimiento del parque de vehículos",
                                          "Mantenimiento de clientes",
                                          "Ventas"} );

        int opcion = -1;
        GestionSecciones gs = new GestionSecciones(c);
        GestionVehiculos gv = new GestionVehiculos(c);
        GestionVentas gve = new GestionVentas(c);
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
                case 4:
                    gve.showMenu();
            }
        }
        System.out.println("!!!!!Gracias por usar nuestro programa!!!!!");

        serializar(c, nombreFichero);
    }


}
