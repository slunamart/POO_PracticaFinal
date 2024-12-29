import modelo.Concesionario;
import modelo.Vehiculo;
import org.junit.Before;
import org.junit.Test;
import principal.AppConcesionario;

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

}
