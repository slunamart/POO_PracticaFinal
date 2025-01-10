import modelo.Vehiculo;
import org.junit.Test;
import static org.junit.Assert.*;

public class GestionVehiculosTest {

    @Test
    public void equalsWorks(){
        Vehiculo v1 = new Vehiculo();
        Vehiculo v2 = new Vehiculo();
        v1.setModelo("4Latas");
        v2.setModelo("4Latas");
        v1.setMarca("Ford");
        v2.setMarca("Aston martin");
        //tiene que ser assertTrue porque quiero probar el equals
        assertTrue(v1.equals(v2));
    }

}
