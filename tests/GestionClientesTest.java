import modelo.Concesionario;
import org.junit.Before;
import org.junit.Test;
import modelo.Cliente;
import static org.junit.Assert.*;
//TODO quitar imports sin usar

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



}