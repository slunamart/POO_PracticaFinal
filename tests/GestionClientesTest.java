//import org.junit.Test;

import modelo.Cliente;
import modelo.Concesionario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionClientesTest {

    private Concesionario c;

    @Test
    public void anadirCliente(){
        Cliente cl = new Cliente();
        this.c.addCliente(cl);
        assertTrue(c.sizeVehiculo() > 0 );
    }

}