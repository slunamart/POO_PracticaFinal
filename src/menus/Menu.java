package menus;

import ES.MyInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    String titulo;
    List<String> opciones;

    public Menu ( String titulo, String[] opciones){
        this.titulo = titulo;
        this.opciones = Arrays.asList( opciones );
    }

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
        System.out.println("=========================================================");
        return opcionNumber;
    }

    private boolean opcionEsValida( int opcion ){
        return 0 <= opcion && opcion <= opciones.size();
    }


}
