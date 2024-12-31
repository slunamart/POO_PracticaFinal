package modelo;

import ES.MyInput;
import menus.Menu;



public class GestionClientes {

    private Concesionario c;

    public GestionClientes(Concesionario c){
        this.c = c;
    }

    public void showMenu() {

        // * FALTA IMPLEMENTAR sizeCliente EN CONCESIONARIO *
        /*
        if (this.c.sizeCliente() == 0){
            System.out.println("ERROR: No hay clientes disponibles.");
            if(MyInput.yesNoQuestion("¿Desea dar de alta un cliente? [Y/N]")){
                this.GestionClientes.altaCliente();
            }
        }
        */

        /*
        if (this.c.sizeCliente() == 0){
            System.out.println("ERROR: No hay clientes disponibles.);
            return;
        }
        */


        Menu menu_clientes = new Menu("Menú Clientes",
                new String[]{"Añadir Cliente",
                        "Información de cliente",
                        "Información de todos los clientes",
                        "Información de clientes que desean recibir publicidad"});

        int opcion;
        Cliente cl;

        do{
            opcion = menu_clientes.show();
            switch(opcion){
                case 1:
                    altaCliente();
                    break;
                case 2:
                    //infoCliente();
                    break;
                case 3:
                    //infoTodosClientes();
                    break;
                case 4:
                    //infoClientesPublicidad();
                    break;
                default:

            }
        }while(opcion!=0);
    }

    // Implementar este código en Concesionario, e implementar para tener los arraylist de clientes públicos, como los de secciones y vehículos.

    /*public Cliente buscarPorDNI(String dni) {
        for (Cliente cliente : clientes) { // Recorrer la lista de clientes
            if (cliente.getDNI().equals(dni)) { // Compara el DNI
                return cliente; // Devuelve el cliente si encuentra uno con el mismo DNI
            }
        }
        return null; // Si no encuentra un cliente con ese DNI, devuelve null
    }
    */

    public void altaCliente(){
        Cliente cliente = new Cliente();

        System.out.println("========================================================");
        System.out.println("Alta de un cliente");
        System.out.println("--------------------------------------------------------");
        cliente.setDNI( MyInput.readString("DNI: "));
        cliente.setNombre(MyInput.readString("Nombre: "));
        cliente.setApellidos(MyInput.readString("Apellidos: "));
        cliente.setTelefono(MyInput.readString("Número de teléfono: "));
        cliente.setDeseaInfo(MyInput.yesNoQuestion("¿Desea recibir notificaciones publicitarias? [Y/N]"));
        // *FALTA IMPLEMENTAR add.Cliente() EN CONCESIONARIO*
        // c.addCliente(cliente);
    }

    // Implementar buscador de cliente por DNI, si existe, devuelve el cliente, y si no existe, devuelve null;
    public void infoCLiente(){

    }


}
