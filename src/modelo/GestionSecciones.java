package modelo;

import ES.MyInput;
import menus.Menu;

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
                    break;
                case 3: // modificacion de una seccion
                    break;
                case 4: // consulta de secciones disponibles
                    break;
            }
        }
    }

    public void altaSeccion(){
        System.out.println("Alta de una seccion");
        Seccion seccion = new Seccion();
        System.out.println("El identificador de sección puede ser algo como");
        System.out.println("\"SUV\", o \"utilitario\"");
        seccion.setID( MyInput.readString("Identificador de la sección: ") );
        seccion.setDescripcion( MyInput.readString("Descripción de la sección: "));
        this.c.addSeccion( seccion );
    }


}
