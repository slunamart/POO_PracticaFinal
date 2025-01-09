package modelo;

import ES.MyInput;
import menus.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones relacionadas con los vehículos en un concesionario.
 * Permite realizar altas, bajas, modificaciones, consultas y otras operaciones específicas con vehículos.
 * Gestiona los vehículos en relación con las secciones del concesionario.
 *
 * @author Santiago Luna Martínez
 * @author Javier Herrería Martín
 */
public class GestionVehiculos implements Gestionable{

    private final Concesionario c;
    private GestionSecciones gestionSecciones;


    /**
     * Constructor de la clase GestionVehiculos.
     * Inicializa la gestión con el concesionario asociado.
     * @param c Concesionario donde se gestionarán los vehículos.
     */
    public GestionVehiculos(Concesionario c){
        this.c = c;
    }

    /**
     * Muestra el menú de opciones relacionadas con la gestión de vehículos.
     * Permite al usuario interactuar para realizar altas, bajas, modificaciones y consultas de vehículos.
     */
    @Override
    public void showMenu() {

        // si no hay secciones disponibles, no podrá operarse con los
        // vehículos
        if( this.c.sizeSeccion() == 0 ){
            System.out.println("No hay secciones disponibles: hasta ");
            System.out.println("que no se cree una, no podrá operar ");
            System.out.println("con los vehiculos");
            if( MyInput.yesNoQuestion( "Desea crear una?" ) ){
                this.gestionSecciones.alta();
            }
        }

        // al llegar a este punto, la sección puede existir o no,
        // por lo que no queda más remedio que verificarlo de nuevo
        if( this.c.sizeSeccion() == 0 ){
            System.out.println("No hay secciones disponibles");
            return;
        }


        Menu menu_vehiculos = new Menu("Menú vehiculos",
                new String[]{"Alta de coches",
                        "Baja coches",
                        "Modificar coche",
                        "Consultar coches",
                        "Aumentar stock",
                        "Mostrar todos los coches en una sección"});
        int opcion;
        do{
            opcion = menu_vehiculos.show();
            switch (opcion){
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
                    consultaVehiculo();
                    break;
                case 5:
                    aumentarStock();
                    break;
                case 6:
                    cochePorSeccion();
                    break;
                default:
                    // no hacer nada
            }
        } while( opcion != 0 );
    }

    /**
     * Muestra la información detallada de un vehículo.
     * @param v Vehículo cuya información será mostrada.
     */
    public void showVehiculo(Vehiculo v){
        Seccion s = v.getSeccion();
        System.out.println("--------------------------------------------------------");
        System.out.println("Sección: "+ s.getID());
        System.out.println("Marca: " + v.getMarca());
        System.out.println("Modelo: " + v.getModelo());
        System.out.println("Año de fabricación: " + v.getAnioFabric());
        System.out.println("Precio por unidad: " + v.getPrecioBase());
        System.out.println("Stock: " + v.getStock());
    }

    /**
     * Da de alta un nuevo vehículo en el concesionario.
     * Solicita al usuario los datos necesarios y los registra en el sistema.
     */
    @Override
    public void alta(){
        Vehiculo vehiculo = new Vehiculo();

        System.out.println("========================================================");
        System.out.println("Elije la sección en el que irá el vehículo");
        vehiculo.setSeccion( gestionSecciones.elige() );
        System.out.println("========================================================");
        System.out.println("Alta de un vehículo");
        System.out.println("--------------------------------------------------------");
        vehiculo.setMarca( MyInput.readString("Marca") );
        vehiculo.setModelo( MyInput.readString("Modelo") );
        vehiculo.setAnioFabric( Integer.toString( MyInput.readInt("Año de fabricacion") ) ) ;
        vehiculo.setPrecioBase( BigDecimal.valueOf( MyInput.readDouble("precioBase") ) );
        vehiculo.setStock( MyInput.readInt("Stock") );
        System.out.println("========================================================");
        c.addVehiculo( vehiculo);
    }

    /**
     * Da de baja un vehículo del concesionario.
     * Solicita al usuario el modelo del vehículo a eliminar y ajusta su stock.
     */
    @Override
    public void baja(){
        System.out.println("Baja de un vehículo");
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setModelo( MyInput.readString("Introduce el nombre del modelo") );
        if( c.existeVehiculo( vehiculo ) ){
            int qtty = MyInput.readInt("Cuántos quieres dar de baja") ;
            vehiculo.setStock(qtty);
        }
    }

    /**
     * Consulta y muestra la información de todos los vehículos registrados en el concesionario.
     */
    private void consultaVehiculo(){
        if(c.getArrayVehiculos().isEmpty()) {
            System.out.println("No hay vehículos aún en este concesionario");
        }
        else{
            for (Vehiculo v2 : c.getArrayVehiculos()) {
                showVehiculo(v2);
            }
        }
        MyInput.waitForIntro();
    }

    /**
     * Permite al usuario seleccionar un vehículo de la lista disponible.
     * Opcionalmente, puede validarse que el stock del vehículo sea mayor a cero.
     * @param validarStock Indica si debe validarse el stock del vehículo.
     * @return El vehículo seleccionado, o {@code null} si no se selecciona ningún vehículo.
     */
    public Vehiculo elige(boolean validarStock ){
        List<String> marcaModeloStock = new ArrayList<>();
        for (Vehiculo v2 : c.getArrayVehiculos()){
            marcaModeloStock.add( v2.getMarca() + " - " + v2.getModelo() + " (" + v2.getStock() + ")" );
        }
        if (marcaModeloStock.size() == 1){
            System.out.println("Como solo hay un coche se selecciona automáticamente...");
            return c.getArrayVehiculos().get(0);
        }
        else {
            Menu menu_Vehiculos = new Menu("Lista de vehículos",
                    marcaModeloStock.toArray(new String[0]));
            int opcion = menu_Vehiculos.show();
            if (opcion == 0)
                return null;
            else{
                if( validarStock ){
                    if(c.getArrayVehiculos().get(opcion - 1).getStock() <= 0){
                        System.out.println("No quedan existencias de este modelo, elige otro o cancela");
                        return elige( true ); //mantenemos la validación de stock al reentrar al menú
                    }
                    else{
                        return c.getArrayVehiculos().get(opcion - 1);
                    }
                }
                else{
                    return c.getArrayVehiculos().get(opcion - 1);
                }
            }
        }

    }

    /**
     * Modifica los datos de un vehículo seleccionado por el usuario.
     * Permite cambiar atributos como marca, modelo, año, precio y stock.
     */
    @Override
    public void modificacion(){
        do{
            System.out.println("Escribe el modelo del coche que quieres modificar");
            Vehiculo vehiculo = elige(false);
            System.out.println("Sección actual del vehículo" + vehiculo.getSeccion().getID());
            System.out.println("Elige la nueva sección, para mantenerlo en la misma reelige la sección");
            vehiculo.setSeccion(gestionSecciones.elige());
            vehiculo.setMarca( MyInput.modString( "Nueva marca del vehiculo", vehiculo.getMarca() ) );
            vehiculo.setModelo( MyInput.modString( "Nuevo modelo del vehiculo", vehiculo.getModelo() ) );
            vehiculo.setAnioFabric( MyInput.modString( "Nuevo año de fabricación", vehiculo.getAnioFabric() ) );
            vehiculo.setPrecioBase(BigDecimal.valueOf(
                    MyInput.modDouble( "Nuevo precio base",
                            vehiculo.getPrecioBase().doubleValue() )));
            vehiculo.setStock( MyInput.modInt( "Nuevo stock", vehiculo.getStock() ) );
            System.out.println("Nuevos datos del vehículo");
            showVehiculo(vehiculo);
        }while(MyInput.yesNoQuestion("¿Quieres modificar los datos de otro vehículo?"));

    }

    /**
     * Aumenta el stock de un vehículo específico.
     * Solicita al usuario el modelo y el nuevo stock.
     */
    public void aumentarStock(){
        System.out.println("Escribe el modelo del coche que quieres modificar");
        Vehiculo vehiculo = elige(false);
        vehiculo.setStock(MyInput.modInt("stock actual " , vehiculo.getStock() ) );
    }

    /**
     * Muestra todos los vehículos agrupados por sus respectivas secciones.
     */
    public void cochePorSeccion(){
        List<Vehiculo> vehiculos = c.getArrayVehiculos();
        List<Seccion> secciones = c.getArraySecciones();
        for (Seccion s : secciones){
            System.out.println();
            System.out.println(s.getID());
            for (Vehiculo v : vehiculos){
                if (v.getSeccion().equals(s)){
                    showVehiculo(v);
                }
            }
        }
    }

    public GestionSecciones getSecciones() {
        return gestionSecciones;
    }

    public void setSecciones(GestionSecciones gestionSecciones) {
        this.gestionSecciones = gestionSecciones;
    }


}
