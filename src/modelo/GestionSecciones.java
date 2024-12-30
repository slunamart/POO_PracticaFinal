package modelo;

import ES.MyInput;
import menus.Menu;

import java.util.ArrayList;
import java.util.List;

public class GestionSecciones {

    private Concesionario c;

    public GestionSecciones( Concesionario c ){
        this.c = c;
    }

    public void showMenu(){
        Menu secciones = new Menu( "Menú Secciones",
                new String[]{ "Alta de una sección",
                        "Baja de una sección",
                        "Modificación de una sección",
                        "Consulta de las secciones disponibles"} );

        int opcion = -1;
        while( opcion != 0 ){
            opcion = secciones.show();
            switch( opcion ){
                case 1: // alta de una seccion
                    altaSeccion();
                    break;
                case 2: // baja de una sección
                    bajaSeccion();
                    break;
                case 3: // modificacion de una seccion

                    break;
                case 4: // consulta de secciones disponibles
                    mostrarSecciones();
                    break;
            }
        }
    }

    public Seccion eligeSeccion(){
        List<String> IDsecciones = new ArrayList<>();
        for (Seccion s2 : c.getArraySecciones()){
            IDsecciones.add( s2.getID() );
        }
        Menu menu_secciones = new Menu("Menú secciones",
                                      IDsecciones.toArray(new String[0] ) );
        int opcion = menu_secciones.show();
        if( opcion == 0 )
            return c.getSinSeccion();
        else
            return c.getArraySecciones().get( opcion -1 );


    }

    public void altaSeccion(){
        System.out.println("Alta de una seccion");
        Seccion seccion = new Seccion();
        System.out.println("El identificador de sección puede ser algo como");
        System.out.println("\"SUV\", o \"utilitario\"");
        seccion.setID( MyInput.readString("Identificador de la sección") );
        seccion.setDescripcion( MyInput.readString("Descripción de la sección") );
        this.c.addSeccion( seccion );
    }

    public void bajaSeccion(){
        System.out.println("Baja de una sección");
        Seccion seccion = new Seccion();
        seccion.setID(MyInput.readString("Di el nombre de la sección que quieres dar de baja") );
        if (c.existeSeccion(seccion)){
            c.rmSeccion(seccion);
        }
        else{
            System.out.println("no existe tal sección con ese ID");
        }

    }

    public void mostrarSecciones(){
        if(c.getArraySecciones().isEmpty()) {
            System.out.println("No hay secciones aún en este concesionario");
        }
        else{
            System.out.println("=======================================================");
            for (Seccion s2 : this.c.getArraySecciones()) {
                System.out.println("---------------------------------------------------");
                System.out.println("Nombre: " + s2.getID());
                System.out.println("Descripción: " + s2.getDescripcion());
            }
        }
        System.out.println("=======================================================");
        MyInput.waitForIntro();
    }

}
