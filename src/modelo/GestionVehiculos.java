package modelo;

import ES.MyInput;
import menus.Menu;

import java.math.BigDecimal;

public class GestionVehiculos {

    private Concesionario c;
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


        Menu menu_vehiculos = new Menu("Menú menu vehiculos",
                new String[]{"Alta de coches",
                        "Baja coches",
                        "Consultar coches",
                        "Aumentar stock",
                        "Mostrar todos los coches en una sección"});
        int opcion;
        Vehiculo v;
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
                    consultaVehiculo();
                    break;
                case 4: System.out.println("Aumentar stock"); break;
                case 5: System.out.println("Mostrar todos los coches en una sección"); break;
                default:
                    // no hacer nada
            }
        } while( opcion != 0 );
    }

    public void showVehiculo(Vehiculo v){
        Seccion s = v.getSeccion();
        System.out.println("--------------------------------------------------------");
        if( s != null )
            System.out.println("Sección: "+ s.getID());
        System.out.println("Marca: " + v.getMarca());
        System.out.println("Modelo: " + v.getModelo());
        System.out.println("Año de fabricación: " + v.getAnioFabric());
        System.out.println("Precio por unidad: " + v.getPrecioBase());
        System.out.println("Stock: " + v.getStock());
    }


    private void altaVehiculo(){
        Vehiculo vehiculo = new Vehiculo();

        MyInput.limpiarConsola();
        System.out.println("========================================================");
        System.out.println("Elije la sección en el que irá el vehículo");
        vehiculo.setSeccion( gestionSecciones.eligeSeccion() );
        System.out.println("========================================================");
        MyInput.limpiarConsola();

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

    private void modVehiculo(){
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setModelo(MyInput.readString("Escribe el modelo del coche que quieres modificar") );
        vehiculo = c.getVehiculo(vehiculo);
        System.out.println("Elije la característica del coche que quieres modificar");
        showVehiculo(vehiculo);
        switch( MyInput.readInt("Escoga opción") ){
            case 1: break;
            default: break;
        }

    }

    public GestionSecciones getSecciones() {
        return gestionSecciones;
    }

    public void setSecciones(GestionSecciones gestionSecciones) {
        this.gestionSecciones = gestionSecciones;
    }


}
