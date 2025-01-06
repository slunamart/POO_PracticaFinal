import modelo.Concesionario;
import modelo.Vehiculo;
import org.junit.Test;
import modelo.Cliente;
import static org.junit.Assert.*;

public class GestionClientesTest {

    private Concesionario c;

    @Test
    public void anadirCliente(){
        Cliente cl = new Cliente();
        this.c.addCliente(cl);
        assertTrue(c.sizeCliente() > 0 );
    }

    @Test
    public void borrarCliente(){
    //no hay test porque no borramos clientes
    assertTrue(true);
    }

}