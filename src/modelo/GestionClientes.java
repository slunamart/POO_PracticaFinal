package modelo;

import ES.MyInput;
import menus.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones relacionadas con los clientes de un concesionario.
 * Permite realizar altas, bajas, consultas y mostrar información detallada de los clientes.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class GestionClientes implements Gestionable{

    private final Concesionario c;

    /**
     * Constructor de la clase GestionClientes.
     * Inicializa la gestión con el concesionario asociado.
     * @param c Concesionario donde se gestionarán los clientes.
     */
    public GestionClientes(Concesionario c){
        this.c = c;
    }

    /**
     * Muestra el menú de opciones relacionadas con la gestión de clientes.
     * Permite al usuario interactuar con el sistema para gestionar clientes.
     */
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
                        "Dar de baja a un cliente",
                        "Modificar datos clientes",
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
                    baja();
                    break;
                case 3:
                    modificacion();
                    break;
                case 4:
                    consultaCliente();
                    break;
                case 5:
                    infoTodosClientes();
                    break;
                case 6:
                    infoClientesPublicidad();
                    break;
                default:

            }
        }while(opcion!=0);
    }

    /**
     * Permite seleccionar un cliente de la lista de clientes registrados.
     * Muestra un menú con los nombres de los clientes.
     * @return El cliente seleccionado.
     */
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

    /**
     * Da de alta un nuevo cliente en el concesionario.
     * Solicita al usuario los datos necesarios y los registra si no hay conflictos de DNI.
     */
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

    /**
     * Da de baja un cliente del concesionario.
     * Solicita al usuario el DNI del cliente a eliminar.
     */
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

    /**
     * Permite modificar los datos de un cliente existente.
     * Solicita al usuario seleccionar un cliente primero para editar sus atributos.
     * Una vez editado, se muestra la información actualizada del cliente.
     * El proceso se repite hasta que el usuario decida no realizar más modificaciones.
     */
    @Override
    public void modificacion(){
        do{
            System.out.println("Eliga el cliente a modificar");
            Cliente cliente = elige();
            cliente.setDNI(MyInput.modString("DNI actual", cliente.getDNI() ) );
            cliente.setNombre(MyInput.modString("Nombre actual", cliente.getNombre() ) );
            cliente.setApellidos(MyInput.modString("Apellidos actuaes", cliente.getApellidos() ) );
            cliente.setTelefono(MyInput.modString("Teléfono actual", cliente.getTelefono() ) );
            cliente.setDeseaInfo(MyInput.modBool("Interés en recibir publicidad", cliente.getDeseaInfo() ) );
            showCliente(cliente);
        }while(MyInput.yesNoQuestion("¿Quieres modificar los datos de otra secció?"));

    }

    /**
     * Consulta y muestra la información de un cliente por su DNI.
     * Si no existe, notifica al usuario.
     */
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

    /**
     * Muestra la información completa de un cliente.
     * @param cl Cliente cuya información será mostrada.
     */
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

    /**
     * Muestra la información de todos los clientes registrados.
     */
    public void infoTodosClientes(){
        if (c.getArrayClientes().isEmpty()){
            System.out.println("Aún no hay clientes en el concesionario.");
        }else{
            for(Cliente cl2 : c.getArrayClientes()){
                showCliente(cl2);
            }
        }
    }

    /**
     * Muestra información de los clientes interesados en recibir publicidad.
     */
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
