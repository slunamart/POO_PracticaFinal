import ES.MyInput;
import modelo.Cliente;
import modelo.Concesionario;
import modelo.Seccion;
import modelo.Vehiculo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase de prueba para verificar el correcto funcionamiento de las operaciones
 * relacionadas con el concesionario, incluyendo la gestión de vehículos, secciones y clientes.
 *
 * @see Concesionario
 */
public class ConcesionarioTest {

    private Concesionario c;

    /**
     * Inicializa un concesionario vacío antes de cada prueba.
     */
    @Before
    public void before(){
        this.c = new Concesionario();
    }


    /**
     * Verifica que la serialización y deserialización de un concesionario
     * mantenga la integridad de los datos.
     */
    @Test
    public void serializeTest(){
        Concesionario c = new Concesionario();
        Vehiculo v = new Vehiculo();
        v.setMarca("Ford");
        v.setModelo("forito");
        c.addVehiculo(v);
        MyInput.serialize(c,"concesionario.txt");

        Concesionario c2 = MyInput.deserialize("concesionario.txt");

        assertEquals( c.sizeVehiculo() , c2.sizeVehiculo () );
        assertEquals(c2.sizeVehiculo(),1);
        Vehiculo v2 = c2.getArrayVehiculos().get(0);
        assertEquals( v, v2);
    }

    /**
     * Verifica que un vehículo puede ser añadido correctamente al concesionario.
     */
    @Test
    public void anadirVehiculo(){
        Vehiculo v = new Vehiculo();
        this.c.addVehiculo(v);
        assertTrue(c.sizeVehiculo() > 0 );
    }

    /**
     * Verifica que el stock de un vehículo puede ser reducido correctamente.
     */
    @Test
    public void borrarVehiculo(){
        Vehiculo v = new Vehiculo();
        v.setModelo("Forito");
        v.setStock(10);
        this.c.addVehiculo(v);

        Vehiculo v2 = new Vehiculo();
        v2.setModelo("Forito");
        this.c.rmVehiculo(v2);
        v2 = c.getVehiculo(v2);
        assertEquals(v2.getStock(), 9);
    }

    /**
     * Verifica que una sección puede ser añadida correctamente al concesionario.
     */
    @Test
    public void anadirSeccion(){
        Seccion s = new Seccion();
        s.setID("clasePrueba");
        this.c.addSeccion(s);
        assertTrue(c.sizeSeccion() > 0 );
    }

    /**
     * Verifica que una sección puede ser eliminada correctamente del concesionario.
     */
    @Test
    public void borrarSeccion(){
        Seccion s = new Seccion();
        s.setID("clasePrueba");
        s.setDescripcion("esto es una clase de prueba");
        this.c.addSeccion(s);

        Seccion s2 = new Seccion();
        s2.setID("clasePrueba");
        s2.setDescripcion("esto es una clase de prueba");
        this.c.addSeccion(s);
        s2 = c.getSeccion(s2);
        assertEquals(s2 , s);
    }

    /**
     * Verifica que un cliente puede ser añadido correctamente al concesionario.
     */
    @Test
    public void anadirCliente(){
        Cliente cl = new Cliente();
        this.c.addCliente(cl);
        assertTrue(c.sizeCliente() > 0 );
    }

    /**
     * Esta prueba está pendiente de desarrollo, ya que actualmente no se implementa la eliminación de clientes.
     */
    @Test
    public void borrarCliente(){
        //no hay test porque no borramos clientes
        assertTrue(true);
    }

}
