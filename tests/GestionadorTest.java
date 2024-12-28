import org.junit.Before;
import org.junit.Test;
import modelo.Cliente;
import modelo.Gestionador;

import static org.junit.Assert.*;

public class GestionadorTest {

    private Gestionador gestCli;

    @Before
    public void before (){
        gestCli = new Gestionador();
    }

    @Test
    public void anadirFunciona(){
        Cliente c1 = new Cliente( "125GF", "Santiago", "Luna Martinez");
        gestCli.add( c1 );
        assertNotNull( gestCli );
        assertTrue( !gestCli.isEmpty() );
        assertTrue( gestCli.size() == 1 );

        Cliente c2 = new Cliente( "125G3434F", "Juan", "Sampedro García");
        gestCli.add( c2 );
        assertNotNull( gestCli );
        assertTrue( !gestCli.isEmpty() );
        assertTrue( gestCli.size() == 2 );

    }

    @Test
    public void borrarFunciona(){
        Cliente c1 = new Cliente( "125GF", "Santiago", "Luna Martinez");
        gestCli.add( c1 );
        assertNotNull( gestCli );
        assertTrue( !gestCli.isEmpty() );
        assertTrue( gestCli.size() == 1 );

        Cliente c2 = new Cliente( "125GF", "Santiago", "Luna Martinez");
        gestCli.rm( c2 );
        assertTrue( gestCli.isEmpty() );
        assertTrue( gestCli.size() == 0 );

    }




}
