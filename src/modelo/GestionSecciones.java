package modelo;

import ES.MyInput;
import menus.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones relacionadas con las secciones de un concesionario.
 * Permite realizar altas, bajas, consultas y seleccionar secciones disponibles.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class GestionSecciones implements  Gestionable{

    private final Concesionario c;

    /**
     * Constructor de la clase GestionSecciones.
     * Inicializa la gestión con el concesionario asociado.
     * @param c Concesionario donde se gestionarán las secciones.
     */
    public GestionSecciones( Concesionario c ){
        this.c = c;
    }

    /**
     * Muestra el menú de opciones relacionadas con la gestión de secciones.
     * Permite al usuario interactuar para agregar, eliminar, modificar y consultar secciones.
     */
    @Override
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
                    alta();
                    break;
                case 2: // baja de una sección
                    baja();
                    break;
                case 3: // modificacion de una seccion
                    modificacion();
                    break;
                case 4: // consulta de secciones disponibles
                    mostrarSecciones();
                    break;
            }
        }
    }

    /**
     * Permite al usuario seleccionar una sección de las disponibles en el concesionario.
     * Si no se selecciona ninguna, retorna la sección predeterminada "Sin Sección".
     * @return La sección seleccionada.
     */
    public Seccion elige(){
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

    /**
     * Da de alta una nueva sección en el concesionario.
     * Solicita al usuario los datos necesarios para registrar una nueva sección.
     */
    @Override
    public void alta(){
        System.out.println("=========================================================");
        System.out.println("Alta de una sección");
        Seccion seccion = new Seccion();
        System.out.println("El identificador de sección puede ser algo como");
        System.out.println("\"SUV\", o \"utilitario\"");
        seccion.setID( MyInput.readString("Identificador de la sección") );
        seccion.setDescripcion( MyInput.readString("Descripción de la sección") );
        this.c.addSeccion( seccion );
    }

    /**
     * Da de baja una sección del concesionario.
     * Solicita al usuario el identificador de la sección a eliminar.
     */
    @Override
    public void baja(){
        System.out.println("Elige la sección a dar de baja" );
        Seccion seccion = elige();
        if(!seccion.equals( c.getSinSeccion() ) ) {
            c.rmSeccion(seccion);
        }
    }

    /**
     * Permite modificar los datos de una sección existente.
     * Solicita al usuario seleccionar una sección para editar sus atributos.
     * El identificador y la descripción actuales de la sección pueden ser modificados.
     * Una vez editada, se muestra la información actualizada de la sección.
     * El proceso se repite hasta que el usuario decida no realizar más modificaciones.
     */
    @Override
    public void modificacion(){
        do{
            System.out.println("Escribe la sección a modificar");
            Seccion seccion = elige();
            seccion.setID(MyInput.modString("ID acutal", seccion.getID() ) );
            seccion.setDescripcion(MyInput.modString("Descripción acutal", seccion.getDescripcion() ) );
            showSeccion(seccion);
        }while(MyInput.yesNoQuestion("¿Quieres modificar los datos de otra sección?"));

    }

    /**
     * Muestra la información detallada de una sección.
     * Imprime el identificador único y la descripción de la sección especificada.
     * @param s Sección cuya información será mostrada.
     */
    public void showSeccion(Seccion s){
        System.out.println("--------------------------------------------------------");
        System.out.println("ID: " + s.getID());
        System.out.println("Descripción: " + s.getDescripcion());
    }

    /**
     * Muestra todas las secciones disponibles en el concesionario.
     * Incluye el nombre y la descripción de cada sección.
     */
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