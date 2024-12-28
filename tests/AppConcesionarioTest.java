import org.junit.Before;
import org.junit.Test;
import principal.AppConcesionario;

import static org.junit.Assert.*;

public class AppConcesionarioTest {

    private AppConcesionario c;

    @Before
    public void antesDeMetodo (){
        this.c = new AppConcesionario();
    }

    @Test
    public void sumaFunciona() {
        assertTrue(c.suma(3,5) == 8);
    }

}
