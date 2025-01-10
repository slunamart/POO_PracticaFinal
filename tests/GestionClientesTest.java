import modelo.Concesionario;
import org.junit.Before;
import org.junit.Test;
import modelo.Cliente;
import static org.junit.Assert.*;

/**
 * Clase de prueba para validar la gestión de clientes en el concesionario.
 *
 * @see modelo.GestionClientes
 */
public class GestionClientesTest {

    private Concesionario c;

    /**
     * Inicializa un concesionario vacío antes de cada prueba.
     */
    @Before
    public void before(){
        this.c = new Concesionario();
    }

    @Test
    public void equalsWorks(){
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        c1.setDNI("12345A");
        c2.setDNI("12345A");
        c1.setNombre("Alfred");
        c1.setNombre("Bob");
        //tiene que ser assertTrue porque quiero probar el equals
        assertTrue(c1.equals(c2));
    }

}