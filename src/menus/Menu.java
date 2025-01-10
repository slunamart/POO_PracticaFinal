package menus;

import ES.MyInput;

import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa un menú interactivo en consola.
 * Permite mostrar una lista de opciones y gestionar la selección del usuario.
 */
public class Menu {

    String titulo;
    List<String> opciones;

    /**
     * Crea un menú con un título y una lista de opciones.
     * @param titulo Título del menú.
     * @param opciones Arreglo de cadenas que representan las opciones del menú.
     */
    public Menu ( String titulo, String[] opciones){
        this.titulo = titulo;
        this.opciones = Arrays.asList( opciones );
    }

    /**
     * Muestra el menú en consola y permite al usuario seleccionar una opción.
     * @return El número de la opción seleccionada por el usuario. Retorna 0 para salir.
     */
    public int show(){
        System.out.println("=========================================================");
        System.out.println( "  " + titulo );
        System.out.println("---------------------------------------------------------");

        System.out.println();

        for(int i = 0; i<opciones.size(); i++){
            System.out.println( i+1 + ". " + opciones.get(i) );
        }

        System.out.println("0. Salir del " + titulo);
        int opcionNumber;
        do {
            System.out.print("Escoja una opción: ");
            opcionNumber = MyInput.readInt();
            if( !this.opcionEsValida(opcionNumber)){
                System.out.println("opcion incorrecta");
            }
        }while( !this.opcionEsValida( opcionNumber ) );
        return opcionNumber;
    }

    /**
     * Verifica si un número corresponde a una opción válida del menú.
     * @param opcion Número de la opción a validar.
     * @return true si la opción es válida, false en caso contrario.
     */
    private boolean opcionEsValida( int opcion ){
        return 0 <= opcion && opcion <= opciones.size();
    }


}