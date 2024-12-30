package modelo;

import ES.MyInput;
import menus.Menu;



public class GestionClientes {

    private Concesionario c;

    public GestionClientes(Concesionario c){
        this.c = c;
    }

    public void showMenu(){

        //if (this.c.sizeCliente)
        Menu menuClientes = new Menu("Menú Clientes",
                new String[]{"Añadir Cliente",
                        "Información de cliente",
                        "Información de todos los clientes",
                        "Información de clientes que desean recibir publicidad"});

        int opcion;
        Cliente cl;

    }






}
