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
        this.c.getVehiculos().add( v );
        assertTrue( this.c.getVehiculos().size() > 0 );
    }

}
