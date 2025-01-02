package modelo;

import ES.MyInput;
import menus.Menu;

import java.util.ArrayList;
import java.util.List;


public class GestionClientes {

    private final Concesionario c;

    public GestionClientes(Concesionario c){
        this.c = c;
    }

    public void showMenu() {

        if (this.c.sizeCliente() == 0){
            System.out.println("ERROR: No hay clientes disponibles.");
            if(MyInput.yesNoQuestion("¿Desea dar de alta un cliente? [S/N]")){
                altaCliente();
            }
        }


        if (this.c.sizeCliente() == 0){
            System.out.println("ERROR: No hay clientes disponibles.");
            return;
        }


        Menu menu_clientes = new Menu("Menú Clientes",
                new String[]{"Añadir Cliente",
                        "Información de cliente",
                        "Información de todos los clientes",
                        "Información de clientes que desean recibir publicidad"});

        int opcion;

        do{
            opcion = menu_clientes.show();
            switch(opcion){
                case 1:
                    altaCliente();
                    break;
                case 2:
                    consultaCliente();
                    break;
                case 3:
                    infoTodosClientes();
                    break;
                case 4:
                    infoClientesPublicidad();
                    break;
                default:

            }
        }while(opcion!=0);
    }

    public Cliente eligeCliente(){
        List<String> clientes = new ArrayList<>();
        for (Cliente cl2 : c.getArrayClientes()){
            clientes.add( cl2.getNombre() + " " + cl2.getApellidos() );
        }
        Menu menu_clientes = new Menu("Menú clientes",
                clientes.toArray(new String[0] ) );
        int opcion = menu_clientes.show();
        if( opcion == 0 ){
            System.out.println("No puedes no elegir un cliente");
            return eligeCliente();
        }
        else{
            return c.getArrayClientes().get(opcion - 1);
        }

    }

    private void altaCliente(){
        Cliente cliente = new Cliente();

        System.out.println("========================================================");
        System.out.println("Alta de un cliente");
        System.out.println("--------------------------------------------------------");

        String dni = MyInput.readString("DNI: ");
        if(c.buscarPorDNI(dni) != null){
            System.out.println("ERROR: El DNI" + dni + "ya está registrado en el sistema.");
            MyInput.waitForIntro("Pulse intro para continuar.");
            return;
        }

        cliente.setDNI(dni);
        cliente.setNombre(MyInput.readString("Nombre"));
        cliente.setApellidos(MyInput.readString("Apellidos"));
        cliente.setTelefono(MyInput.readString("Número de teléfono"));
        cliente.setDeseaInfo(MyInput.yesNoQuestion("¿Desea recibir notificaciones publicitarias? [S/N]"));
        c.addCliente(cliente);
    }

    private void consultaCliente() {
        if (c.getArrayClientes().isEmpty()) {
            System.out.println("Aún no hay clientes en el concesionario.");
        } else {
            String dni = MyInput.readString("Introduzca el DNI del cliente: ");
            Cliente cl2 = c.buscarPorDNI(dni);
            if (cl2 == null){
                System.out.println("ERROR: El DNI " + dni + " no está asociado a ningún cliente.");
            }else{
                showCliente(cl2);
            }
        }
        MyInput.waitForIntro();
    }

    public void showCliente(Cliente cl){
        System.out.println("--------------------------------------------------------");
        System.out.println("DNI: " + cl.getDNI());
        System.out.println("Nombre completo: " + cl.getNombre() + " " + cl.getApellidos());
        System.out.println("Número de teléfono: " + cl.getTelefono());
        System.out.print("Publicidad: ");
        if (cl.getDeseaInfo()){
            System.out.println("Interesado");
        }else{
            System.out.println("No interesado");
        }
    }

    public void infoTodosClientes(){
        if (c.getArrayClientes().isEmpty()){
            System.out.println("Aún no hay clientes en el concesionario.");
        }else{
            for(Cliente cl2 : c.getArrayClientes()){
                showCliente(cl2);
            }
        }
    }

    public void infoClientesPublicidad(){
        if (c.getArrayClientes().isEmpty()){
            System.out.println("Aún no hay clientes en el concesionario.");
        }else{
            for(Cliente cl2 : c.getArrayClientes()){
                if(cl2.getDeseaInfo()){
                    showCliente(cl2);
                }
            }
        }
    }

}
