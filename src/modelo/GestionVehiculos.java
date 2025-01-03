package modelo;

import ES.MyInput;
import menus.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GestionVehiculos {

    private final Concesionario c;
    private GestionSecciones gestionSecciones;


    public GestionVehiculos(Concesionario c){
        this.c = c;
    }

    public void showMenu() {

        // si no hay secciones disponibles, no podrá operarse con los
        // vehículos
        if( this.c.sizeSeccion() == 0 ){
            System.out.println("No hay secciones disponibles: hasta ");
            System.out.println("que no se cree una, no podrá operar ");
            System.out.println("con los vehiculos");
            if( MyInput.yesNoQuestion( "Desea crear una?" ) ){
                this.gestionSecciones.altaSeccion();
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
                    altaVehiculo();
                    break;
                case 2:
                    bajaVehiculo();
                    break;
                case 3:
                    modVehiculo();
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


    public void altaVehiculo(){
        Vehiculo vehiculo = new Vehiculo();

        System.out.println("========================================================");
        System.out.println("Elije la sección en el que irá el vehículo");
        vehiculo.setSeccion( gestionSecciones.eligeSeccion() );
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

    private void bajaVehiculo(){
        System.out.println("Baja de un vehículo");
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setModelo( MyInput.readString("Introduce el nombre del modelo") );
        if( c.existeVehiculo( vehiculo ) ){
            int qtty = MyInput.readInt("Cuántos quieres dar de baja") ;
            vehiculo.setStock(qtty);
        }
    }

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

    public Vehiculo eligeVehiculo( boolean validarStock ){
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
                        return eligeVehiculo( validarStock );
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

    private void modVehiculo(){
        do{
            System.out.println("Escribe el modelo del coche que quieres modificar");
            Vehiculo vehiculo = eligeVehiculo(false);
            // pendiente ver que hacemos con la seccion
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

    public void aumentarStock(){
        System.out.println("Escribe el modelo del coche que quieres modificar");
        Vehiculo vehiculo = eligeVehiculo(false);
        vehiculo.setStock(MyInput.modInt("stock actual " , vehiculo.getStock() ) );
    }

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