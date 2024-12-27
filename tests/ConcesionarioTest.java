import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConcesionarioTest {

    private Concesionario c;

    @Before
    public void antesDeMetodo (){
        this.c = new Concesionario();
    }

    @Test
    public void sumaFunciona() {
        assertTrue(c.suma(3,5) == 8);
    }

}
