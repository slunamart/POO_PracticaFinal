import ES.MyInput;
import modelo.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//TODO quitar imports sin usar


/**
 * Clase de prueba para validar la gestión de secciones en el concesionario.
 *
 * @see modelo.GestionSecciones
 */
public class GestionSeccionesTest {

    private Concesionario c;

    /**
     * Inicializa un concesionario vacío antes de cada prueba.
     */
    @Before
    public void before(){
        this.c = new Concesionario();
    }

}
