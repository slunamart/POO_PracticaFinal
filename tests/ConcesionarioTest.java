import ES.MyInput;
import modelo.Concesionario;
import modelo.Vehiculo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConcesionarioTest {

    private Concesionario c;

    @Before
    public void before(){
        this.c = new Concesionario();
    }

    @Test
    public void anadirVehiculo(){
        Vehiculo v = new Vehiculo();
        this.c.addVehiculo(v);
        assertTrue(c.sizeVehiculo() > 0 );
    }

    @Test
    public void borrarVehiculo(){
        Vehiculo v = new Vehiculo();
        v.setModelo("Forito");
        v.setStock(10);
        this.c.addVehiculo(v);

        Vehiculo v2 = new Vehiculo();
        v2.setModelo("Forito");
        this.c.rmVehiculo(v2,-1);
        v2 = c.getVehiculo(v2);
        assertEquals(v2.getStock(), 9);
    }

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
}
