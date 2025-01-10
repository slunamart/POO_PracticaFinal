import modelo.*;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Clase de prueba para validar la gestión de secciones en el concesionario.
 *
 * @see modelo.GestionSecciones
 */
public class GestionSeccionesTest {

    @Test
    public void equalsWorks(){
        Seccion s1 = new Seccion();
        Seccion s2 = new Seccion();
        s1.setID("alpha");
        s2.setID("alpha");
        s1.setDescripcion("uno");
        s2.setDescripcion("dos");
        //tiene que ser assertTrue porque quiero probar el equals
        assertTrue(s1.equals(s2));
    }
}
