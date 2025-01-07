package modelo;

import ES.MyInput;
import menus.Menu;

import java.util.ArrayList;
import java.util.List;


public class GestionClientes implements  Gestionable{

    private final Concesionario c;

    public GestionClientes(Concesionario c){
        this.c = c;
    }

    @Override
    public void showMenu() {
        if (this.c.sizeCliente() == 0){
            System.out.println("ERROR: No hay clientes disponibles.");
            if(MyInput.yesNoQuestion("¿Desea dar de alta un cliente? [S/N]")){
                alta();
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
                    alta();
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

    public Cliente elige(){
        List<String> clientes = new ArrayList<>();
        for (Cliente cl2 : c.getArrayClientes()){
            clientes.add( cl2.getNombre() + " " + cl2.getApellidos() );
        }
        Menu menu_clientes = new Menu("Menú clientes",
                clientes.toArray(new String[0] ) );
        int opcion = menu_clientes.show();
        if( opcion == 0 ){
            System.out.println("No puedes no elegir un cliente");
            return elige();
        }
        else{
            return c.getArrayClientes().get(opcion - 1);
        }

    }

    @Override
    public void alta(){
        Cliente cliente = new Cliente();
        System.out.println("Alta de un cliente");
        System.out.println("---------------------------------------------------------");

        String dni = MyInput.readString("DNI");
        if(c.buscarPorDNI(dni) != null){
            System.out.println("ERROR: El DNI " + dni + " ya está registrado en el sistema.");
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

    @Override
    public void baja(){
        System.out.println("Baja de un cliente");
        System.out.println("---------------------------------------------------------");
        String dni = MyInput.readString("DNI del cliente que desea eliminar: ");
        Cliente cl = c.buscarPorDNI(dni);
        if(cl == null){
            System.out.println("ERROR: El DNI" + dni + " no está registrado en el sistema.");
            MyInput.waitForIntro("Pulse intro para continuar.");
        }else{
            c.rmCliente(cl);
        }
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
